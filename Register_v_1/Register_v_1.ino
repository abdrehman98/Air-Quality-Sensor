
// -------------------------// Internal/ External Import
#include <ESP8266WiFi.h>    // Wifi Liberary (Node MCU Core)
#include <ESP8266httpUpdate.h>
#include <ESP8266HTTPClient.h>     
#include <WiFiClient.h>
#include <string.h> 
#include <WiFiServer.h>     // TCP/Server    (Node MCU Core)
#include <EEPROM.h>         // Accessing EEPROM (Arduino)
#include <ArduinoJson.h>
#include <StorageIO.h>      // To save SSID/Password/Latitude/Longitude https://github.com/Zeeshan-itu/StorageIO

//--------------------------//-----------------------------

//====================================// Device HotSpoT Settings

#define WiFi_HOTSPOT_SSID      "Air Quality"  // Name of Hot Spot
#define WiFi_HOTSPOT_PASSWORD  "saveairsaveplanet"      // Password of Hot spot


#define PORT_SERVER    2525       // Port at which TCP/Server will start
IPAddress server(192,168,0,80);       // the fix IP address of the server

//====================================

//------------------------------------// Globalveriables
                        // It will be used to Read/Write wifidata on ROM

const char * wifi_ssid;
const char * wifi_password;


const char * UserEmail;
const char * HTTP_URL;

bool WIFI_CONNECTED=false;
WiFiClient client;
double latitude;
double longitude;
unsigned int SensorCombination = 1;
String WiFi_SSID;
String WiFi_PASS;
int DEVICE_ID;

//------------------------------------// Server URL
String URL_REGISTRATION = "http://182.180.122.28:8100/registration"; //URL to send email address and sensor combinations
String URL_LOCATION; //URL to send Location
String URL_UPDATE; //URL to update firmware


void setup() {
  Serial.begin(9600); // - Initalized Serial Communication - //
  
    // Attemp to noccet to wifi
  Serial.println("[Setup] Starting...");
  Serial.println("[SETUP] Connecting To Mobile Hotspot");
  WiFi.mode(WIFI_STA);
  
  WiFi.begin(WiFi_HOTSPOT_SSID,WiFi_HOTSPOT_PASSWORD);
  
  while (WiFi.status() != WL_CONNECTED) 
  {
    Serial.print("[CONNECTION STATUS] "); Serial.println(WiFi.status());
    delay(500);
  }
  Serial.println("[SETUP] Connected to Mobile Hotspot.");
  Serial.println("[SETUP] Connecting client with hotspot server.");
  client.connect(server,PORT_SERVER);  
  
  InitialCommunication();
  
  OTA_update_firmware();
  
}

void loop() { 
}


bool connectWifi()        //Connect to WiFi network
{
  WiFi.disconnect(1);
  
  WiFi.mode(WIFI_STA);
  
  WiFi.begin(wifi_ssid,wifi_password);
  Serial.println("[WIFI STATUS] Connecting To WiFi");
  delay(2000);
  
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print("[WIFI CODE] "); Serial.println(WiFi.status());  
    if(WiFi.status()!= WL_DISCONNECTED && WiFi.status() != WL_CONNECTED)
    {
      Serial.println("[WIFI STATUS] Could'nt connect to WiFi");
      return false; 
    }
    delay(500);
  }

  Serial.println("[WIFI STATUS] WiFi Connected");
  Serial.print("[IP] ");Serial.println(WiFi.localIP());

  return true;
}



void showConnectWait(){
  Serial.print('.');
}

WiFiClient TCPGetClient()    //Returns TCP client
{
    // Start TCP Server
  server.begin();

  // Wait for client to connect
  Serial.println("[CLIENT STATUS] Waiting for TCPClient");
  while(true){
    client = server.available();    
    if (client!=NULL){
      Serial.println("[CLIENT STATUS] TCP CLIENT CONNECTED"); 
      return client;
    }
    delay(100);
  }
}
bool SendServerMessage(String url,String message,String& payload, int& httpCode) //Send Http server serialized JSON object and return payload and httpCode
{
       if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
 
         HTTPClient http;    //Declare object of class HTTPClient
     
         http.begin(url);      //Specify request destination
         Serial.print("[SERVER CONNECTION] CONNECTING TO "); Serial.println(url);
         http.addHeader( "Content-Type", "application/json");  //Specify content-type header
         Serial.print("[SERVER CONNECTION] SENDING SERVER MESSAGE : "); Serial.println(message);
         httpCode = http.POST(message);   //Send the request
         String payload = http.getString();                  //Get the response payload
       
         Serial.printf("[HTTP CODE] %d \n",httpCode);   //Print HTTP return code
         Serial.print("[HTTP PAYLOAD] ");Serial.println(payload);    //Print request response payload
       
         http.end();  //Close connection
         return true;
       }
      else{
        Serial.print("Error in WiFi connection");   
        return false;
     }
}

void SendClientMessage(WiFiClient client,String message)
{
        StaticJsonBuffer<200> jsonBuffer;
        JsonObject& root  = jsonBuffer.createObject();
        root["RES"] = message;
        String json_str;
        root.printTo(json_str);
        client.print(json_str);
}
String sendLocation()            
{
        StaticJsonBuffer<200> jsonBuffer;
        JsonObject& root  = jsonBuffer.createObject();
        root["device_ID"] = DEVICE_ID;
        root["Longitude"] = longitude;
        root["Latitude"] = latitude;
        String json_str;
        root.printTo(json_str);
        String payload;
        int retCode;
        String retval;
        if(SendServerMessage(URL_LOCATION,json_str,payload,retCode))
        {
          if(retCode==HTTP_CODE_OK)
          {  
            StaticJsonBuffer<200> jsonBuffer_2;              
            JsonObject& server_response = jsonBuffer_2.parseObject(payload);
            if(!server_response.success()) 
            {
              Serial.println("parseObject() failed");
            }
            retval=server_response["RES"].asString();
            return retval;
          }
        }
        
}

void InitialCommunication()
{
    String requestType = "{ \"type\" : 1 }";
    client.print(requestType);         
    while(true)
    {            
      String json = client.readStringUntil('\0');
      //====================================== Parsing data received from Mobile Phone ==========================================
          
      const size_t bufferSize = JSON_ARRAY_SIZE(2) + JSON_OBJECT_SIZE(4) + 100;
      DynamicJsonBuffer jsonBuffer(bufferSize);
            
      JsonObject& root = jsonBuffer.parseObject(json);
      if(!root.success()) {
        Serial.println("parseObject() failed");
      }
      UserEmail = root["Email"];
      wifi_ssid = root["SSID"];
      wifi_password = root["Password"];
      latitude = root["Location"][0];
      longitude = root["Location"][1];
          
      //====================================== Displaying parsed data ==========================================================
            
      Serial.print("User Email :");    Serial.println(UserEmail);
      Serial.print("WiFi SSID: ");     Serial.println(wifi_ssid);
      Serial.print("WiFi Password :"); Serial.println(wifi_password);
      Serial.print("Latitude: ");      Serial.println(latitude);
      Serial.print("Longitude: ");     Serial.println(longitude);
          
      if(connectWifi())
      {
        WIFI_CONNECTED=true;
        Serial.println("[WiFi Status] Connected...");
        Serial.println("[WiFi Status] Sending success status.");
        SendClientMessage(client,"WiFi connection successful");
        break;
      }
      else
      {
        Serial.println("[WiFi Status] Not connected\n[WiFi Status] Sending failure message \n Trying Again");
        SendClientMessage(client,"WiFi connection Unsuccessful");
      }
    }
      
    WiFi_SSID = (String)wifi_ssid;
    WiFi_PASS = (String)wifi_password;

      //====================================== Sending Registration data to server ==========================================                        
      String payload;
      int retCode;
        
      String json_str;
        
      StaticJsonBuffer<200> jsonBuffer_1;
      JsonObject& details_1  = jsonBuffer_1.createObject();
        
      details_1["email"] = UserEmail;
      details_1["sensor_comb"] = SensorCombination;
        
      details_1.printTo(json_str);
        
      Serial.print("[JSON STRING] ");Serial.println(json_str);
      if(SendServerMessage(URL_REGISTRATION,json_str,payload,retCode))
      {
        if(retCode==HTTP_CODE_OK)
        {
            //code for parsing payload goes here
            //it gives device id and url to download firmware
            
          StaticJsonBuffer<200> jsonBuffer_2;
              
          JsonObject& server_response = jsonBuffer_2.parseObject(payload);
          if(!server_response.success()) {
            Serial.println("parseObject() failed");
          }
          DEVICE_ID = server_response["device_ID"];
          URL_UPDATE = server_response["url_firmware"].as<String>();
        }
      }
     
        //===================================== Saving credentials in EEPROM ===================================================
      StorageIO rom; 
      rom.reposition();
      rom.writeNextString(String(DEVICE_ID));       
      rom.writeNextString(WiFi_SSID);
      rom.writeNextString(WiFi_PASS);
      rom.writeNextString(String(longitude));
      rom.writeNextString(String(latitude));
        
      
      //===================================== Sending server Location Data ===================================================
      String response=sendLocation();
      Serial.print("[LOCATION RESPONSE] ");Serial.println(response);
}
 
void OTA_update_firmware()
{    
      t_httpUpdate_return ret = ESPhttpUpdate.update(URL_UPDATE);
      switch(ret) {
      case HTTP_UPDATE_FAILED:
          Serial.println("[update] Update failed.");
          break;
      case HTTP_UPDATE_NO_UPDATES:
          Serial.println("[update] Update no Update.");
          break;
      case HTTP_UPDATE_OK:
          Serial.println("[update] Update ok."); // may not called we reboot the ESP
          break;
      }
}

