// -------------------------// Internal/ External Import
#include <ESP8266WiFi.h>    // Wifi Liberary (Node MCU Core)
#include <WiFiClient.h>     // TCP/Client    (Node MCU Core)
#include <WiFiServer.h>     // TCP/Server    (Node MCU Core)
#include <ESP8266httpUpdate.h>
#include <ESP8266HTTPClient.h>     
#include <string.h>         // String        (Arduino)
#include <EEPROM.h>         // Accessing EEPROM (Arduino)
#include <StorageIO.h>      // To save SSID/Password/Latitude/Longitude https://github.com/Zeeshan-itu/StorageIO
#include <Indicator.h>      // To Show some output https://github.com/Zeeshan-itu/Indicator
#include<ArduinoJson.h>
//--------------------------//-----------------------------


//------------------------------------// Globalveriables
StorageIO rom;                         // It will be used to Read/Write wifidata on ROM
Indicator light(D4);                  // To show some output about operations going on. Change pin if you want to use external LED or anything else
//====================================// Wifi Connection Attempt settings
unsigned int SENSORCOMBINATION  ;
#define WIFI_TIME_OUT 12              // Try to connect with wifi for (Seconds)
#define WAIT_TIME     1               // Check for connection status after every (Second)
int connecTime = 0;               // Time Passed Since attempt made to connect to Wifi.
//====================================// Device HotSpoT Settings
#define HOTSPOT_SSID      "Air Quality"  // Name of Hot Spot
#define HOTSPOT_PASSWORD  "saveairsaveplanet"      // Password of Hot spot
#define PORT_APP_SERVER    2525       // Port at which TCP/Server will start
WiFiServer server(PORT_APP_SERVER);   // TCP/Server to recieve password.
//=====================================// TCP Data recieving protocole(Data will be recieved in token seperated string)



#define INTERRUPT_PIN D2
volatile bool setSettings = false;


// ----------------------------- Global Function's
void wifiConnection();           // Try to connect with wifi
void setLocationOnFireBase();    // After connection with wifi send location on FireBase
void resetPassword();            // If unable to connect to internet reset Password
WiFiClient TCPGetClient();
String recieveDataFromClient(WiFiClient);
void parseAndWriteDataOnROM(String);
void showConnecWait();
        //    //      // Incase user interrupts to reset settings
void settingsResetRequest() {setSettings = true;}  // Interrupt pin function


//-----------------------// Dust Sensorveriables
#define CODE_VERSION 0
byte buff[2];
int pin = D8; //DSM501A input D8
unsigned long previousMillis = millis();
unsigned long duration;
unsigned long starttime;
unsigned long endtime;
int device_ID;
double latitude;
double longitude;
unsigned long sampletime_ms = 30000;
float lowpulseoccupancy = 0;
float lowpulseinsec = 0;
float ratio = 0;
float concentration = 0;

String URL_Sensor="";
String URL_Location="";
String URL_UPDATE = "";
String URL_CheckUpdate = "";

void setup() {
  Serial.begin(9600); // - Initalized Serial Communication - //
  
  String d_id=rom.readNextString();
  device_ID=d_id.toInt();
  
  // Attemp to noccet to wifi
  wifiConnection();
  
  // Refresh your location on firebase
  setLocation();

  // setup inteerupt pin for change setting signal
  pinMode(INTERRUPT_PIN, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(INTERRUPT_PIN), settingsResetRequest, HIGH);

  // Initalized pin for dust sensor
  //pinMode(DUST_SENSOR_PIN, INPUT);
  starttime = millis();
}


void loop()
{
  //Accept Sensor JSON string from function
  String sensorData = getSensorData();
  
  String sensorPayload = sendSensor(sensorData);

  Serial.print("[sensorPayload] ");Serial.println(sensorPayload);

  //Check for updates and update if available NOTE: For now update URL is not provided
  if(checkForUpdates()){
    OTA_update_firmware();
  }


  
  if ( setSettings )
    resetSettings();
}

String getSensorData(){}

/*
  Returns True if update is available and False otherwise
*/
bool checkForUpdates()
{
  //Create JSON object for sending data to server to check for updates
  DynamicJsonBuffer jsonBuffer;
  JsonObject& updates = jsonBuffer.createObject();
  updates["DeviceID"] = device_ID;
  updates["CodeVersion"] = CODE_VERSION ;

  String payload;
  int retCode;
  String retval;
  String json_data;
  updates.printTo(json_data);
  if(SendServerMessage(URL_CheckUpdate,json_data,payload,retCode))
  {
          if(retCode==HTTP_CODE_OK)
          {  
            // Indiacte that data is sent
            light.blink(200,1,HIGH,1);
            StaticJsonBuffer<200> jsonBuffer_2;              
            JsonObject& server_response = jsonBuffer_2.parseObject(payload);
            if(!server_response.success()) 
            {
              Serial.println("parseObject() failed");
            }
            retval=server_response["RES"].asString();
            if(retval == "Available")
            {
              return true; 
            }
          }
  }
  return false;
}

/*
  Try to connect to WiFi
*/
void wifiConnection(){

  // Read SSID and password
  char * ssid = rom.readNextString();
  char * password = rom.readNextString();
  WiFi.begin(ssid, password);
  delay(100);
  Serial.println("\nConnection Request:" + (String) ssid);
  Serial.println("Password:" + (String) password);

  // Wair until device is not connected
  while (WiFi.status() != WL_CONNECTED) {
    light.blink();
    Serial.print(".");

    // On connection timeout It will lanuch hotspot
    if (connecTime > WIFI_TIME_OUT){
      Serial.println("\nConnection time out!!");
      resetSettings();
    }

    connecTime += WAIT_TIME;
  }

  // Show my IP Adress
  Serial.print(" connected on ip adress:");
  Serial.println(WiFi.localIP());

}


/*
  Send Locatin to the server
*/
String setLocation() //Needed to be changed
{

  // Read location from storage
  char * lon = rom.readNextString();
  char * lat = rom.readNextString();

  // Print location on Serial(Monitor)
  Serial.print("lon:");
  Serial.println(lon);
  Serial.print("lat:");
  Serial.println(lat);

  // Create location Ojcet in JSON
  DynamicJsonBuffer jsonBuffer;
  JsonObject& location = jsonBuffer.createObject();
  location["Longitude"] = longitude;
  location["Latitude"] = latitude;

  String payload;
  int retCode;
  String retval;
  String json_data;
  location.printTo(json_data);
  if(SendServerMessage(URL_Location,json_data,payload,retCode))
  {
          if(retCode==HTTP_CODE_OK)
          {  
            // Indiacte that data is sent
            light.blink(200,1,HIGH,1);
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

  
  light.blink(400,3,HIGH);
}


/*
Send sensor data to the server
*/

String sendSensor(String jsonString)      //This functions needed to be changed
{

  // create data object in json
  DynamicJsonBuffer jsonBuffer;
  JsonObject& airQualityObject = jsonBuffer.createObject();

  JsonObject& Sensor = airQualityObject.createNestedObject("Sensor");
  airQualityObject["deviceId"] = device_ID;
  airQualityObject["SensorCombination"] = SENSORCOMBINATION; 
  airQualityObject["SensorData"] = jsonString; 
  


  String payload;
  int retCode;
  String retval;
  String json_data;
  airQualityObject.printTo(json_data);
  if(SendServerMessage(URL_Sensor,json_data,payload,retCode))
  {
          if(retCode==HTTP_CODE_OK)
          {  
            // Indiacte that data is sent
            light.blink(200,1,HIGH,1);
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


void startHotSpot(){

  // Start HoTSpoT so that Moblie can connect to this device
  WiFi.softAP(HOTSPOT_SSID, HOTSPOT_PASSWORD);
  Serial.println("Starting Hotspot . . .");
  delay(2000);

  // Show IP/Adress of this device.
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);

}


void resetSettings()
{
  startHotSpot();                         // Turn on HoTSpoT
  WiFiClient client = TCPGetClient();
  String rawData = recieveDataFromClient(client);
  
  parseAndWriteDataOnROM(rawData);
  delay(100);
  ESP.restart();
}

WiFiClient TCPGetClient() 
{

  // Start TCP Server
  server.begin();

  // Wait for client to connect
  Serial.println("\nWaiting for TCPClient");
  while(true){
    showConnecWait();
    WiFiClient client = server.available();

    if (client != NULL)
      return client;
  
  }

}


String recieveDataFromClient(WiFiClient client) 
{

  Serial.println("\nClient avalable");

  // Wait for Client to send data
  while( ! client.available() )
    showConnecWait();

  // Read all data and return
  String s = client.readStringUntil('\0');
  Serial.println("\nRaw Data:" + s);
  return s;
}





void parseAndWriteDataOnROM(String json)
{

  // go-to start of EEPROM
  rom.reposition();

  // GET indecies of Tokens
   const size_t bufferSize = JSON_ARRAY_SIZE(2) + JSON_OBJECT_SIZE(4) + 100;
   
   DynamicJsonBuffer jsonBuffer(bufferSize);
            
   JsonObject& root = jsonBuffer.parseObject(json);
   
   if(!root.success()) {
     Serial.println("parseObject() failed");
   }
  
   String wifi_ssid = root["SSID"].as<String>();
   String wifi_password = root["Password"].as<String>();
   latitude = root["Location"][0];
   longitude = root["Location"][1];
  
  rom.writeNextString(String(device_ID));
  
  rom.writeNextString(wifi_ssid);
  Serial.println(wifi_ssid);

  rom.writeNextString(wifi_password);
  Serial.println(wifi_password);

  rom.writeNextString(String(longitude));
  Serial.println(longitude);
  
  rom.writeNextString(String(latitude));
  Serial.println(latitude);
}


void showConnecWait()
{
  Serial.print('.');
  light.blink(10, 50, HIGH, 0.9);
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

