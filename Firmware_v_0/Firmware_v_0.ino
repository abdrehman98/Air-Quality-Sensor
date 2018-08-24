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
#include <TM1637Display.h> //https://github.com/avishorp/TM1637
 
 
//--------------------------//Indicators
Indicator indicate_mobile_con(D1);
Indicator indicate_wifi_con(D3);
Indicator indicate_firmware_update(D4);

const int CLK = D6; //Set the CLK pin connection to the display
const int DIO = D5; //Set the DIO pin connection to the display

TM1637Display display(CLK, DIO); //set up the 4-Digit Display.
//------------------------------------// Globalveriables
StorageIO rom;                         // It will be used to Read/Write wifidata on ROM
//====================================// Wifi Connection Attempt settings
unsigned int SENSORCOMBINATION=111;   //Sensor Combination of sensor
#define WIFI_TIME_OUT 12              // Try to connect with wifi for (Seconds)
#define WAIT_TIME     1               // Check for connection status after every (Second)
int connecTime = 0;               // Time Passed Since attempt made to connect to Wifi.
//====================================// Device HotSpoT Settings
#define HOTSPOT_SSID      "BjsRvbmjuz"  // Name of Hot Spot
#define HOTSPOT_PASSWORD  "tbw2fb4js6tbwfq9"      // Password of Hot spot
#define PORT_APP_SERVER    2525       // Port at which TCP/Server will start
WiFiServer server(PORT_APP_SERVER);   // TCP/Server to recieve password.
//=====================================// TCP Data recieving protocole(Data will be recieved in token seperated string)



#define INTERRUPT_PIN D2                //pin to manually reseting device
volatile bool setSettings = false;      


// ----------------------------- Global Function's
void wifiConnection();           // Try to connect with wifi
WiFiClient TCPGetClient();
String recieveDataFromClient(WiFiClient);
void parseAndWriteDataOnROM(String);
void showConnecWait();
        //    //      // Incase user interrupts to reset settings
void settingsResetRequest() {setSettings = true;}  // Interrupt pin function


//-----------------------// Global variables
#define CODE_VERSION "AAA"

int pin = D8; //DSM501A input D8
unsigned long previousMillis = millis();
unsigned long duration;
unsigned long starttime;
unsigned long endtime;
int device_ID;
double latitude;
double longitude;

String URL_Sensor="http://182.180.122.28:8100/data";  //URL to send sensor data
String URL_Location="http://182.180.122.28:8100/location";  //URL to send location data
String URL_UPDATE; 
String URL_CheckUpdate = "http://182.180.122.28:8100/checkupdate";  //URL to send request to find out is update available or not

void setup() {
  Serial.begin(9600); // - Initalized Serial Communication - //
  display.setBrightness(0x0a); //set the diplay to maximum brightness
  String d_id=rom.readNextString();   // Read Device ID from EEPROM
  device_ID=d_id.toInt();             // Convert to integer
  Serial.printf("[SETUP] DeviceId = %d",device_ID);
  // Attemp to connect to wifi
  wifiConnection();     
  
  // Refresh your location on server
  setLocation();

  // setup interupt pin for change setting signal
  pinMode(INTERRUPT_PIN, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(INTERRUPT_PIN), settingsResetRequest, HIGH);
  starttime = millis();
}


void loop()
{
  //Accept Sensor JSON string from function
 if(WiFi.status() == WL_CONNECTED)
 {
    String sensorData; 
    int sensor_value = getSensorData(sensorData);          // Received a stringified JSON object from sensor
    display.showNumberDec(sensor_value,false,3,0); //Display the value;

    String sensorPayload = sendSensor(sensorData);  //Sends Sensor data to server and receive servers response
  
    Serial.print("[sensorPayload] ");Serial.println(sensorPayload);
  
    //Check for updates and update if available update the firmware
    if(checkForUpdates()){        
      OTA_update_firmware();
    }

 }
  
  if ( setSettings )      //if device is reset through button press or WiFi is unable to connect then it will reset device
    resetSettings();

}
/*
  This function is not 
*/
int getSensorData(String & json_str)    //For now it is dummy function but it will be replaced
{     
  int retval = 5;
  json_str = "{\"dustValue\":\"" + String(retval) + "\"}";  
  return retval;
}

/*
  Returns True if update is available and False otherwise
*/
bool checkForUpdates() 
{
  //Create JSON object for sending data to server to check for updates
  DynamicJsonBuffer jsonBuffer;
  JsonObject& updates = jsonBuffer.createObject();
  updates["deviceID"] = device_ID;
  updates["codeVersion"] = CODE_VERSION ;

  String payload;
  int retCode;
  String retval;
  String json_data;
  updates.printTo(json_data);
  if(SendServerMessage(URL_CheckUpdate,json_data,payload,retCode))
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
            if(retval == "Update Available")
            {
              URL_UPDATE = server_response["URL"].asString(); 
              Serial.print("[Update URL]");Serial.println(URL_UPDATE);
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
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  indicate_mobile_con.set(LOW) ;  // Set to WiFi connection indicator to LOW
  Serial.println("\nConnection Request:" + (String) ssid);
  Serial.println("Password:" + (String) password);

  // Wair until device is not connected
  while (WiFi.status() != WL_CONNECTED) {

    Serial.print(".");
    indicate_wifi_con.blink();  //Blink to indicate that device is trying to connect to WiFi
    // On connection timeout It will lanuch hotspot
    if (connecTime > WIFI_TIME_OUT){
      Serial.println("\nConnection time out!!");
      indicate_wifi_con.set(LOW); //Set indicator to LOW to show WiFi did'nt connected
      resetSettings();
    }

    connecTime += WAIT_TIME;    
  }

  indicate_wifi_con.set(HIGH);    //set indicator to HIGH to show that WiFi is conencted 
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
  location["device_ID"] = device_ID ;
  location["Longitude"] = lon;
  location["Latitude"] = lat;

  String payload;
  int retCode;
  String retval;
  String json_data;
  location.printTo(json_data);
  if(SendServerMessage(URL_Location,json_data,payload,retCode))
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


/*
Send sensor data to the server
*/

String sendSensor(String jsonString)      //This functions needed to be changed
{

  // create data object in json
  DynamicJsonBuffer jsonBuffer;
  JsonObject& airQualityObject = jsonBuffer.createObject();

  JsonObject& Sensor = airQualityObject.createNestedObject("Sensor");
  airQualityObject["deviceID"] = device_ID;
  airQualityObject["sensorCombination"] = SENSORCOMBINATION; 
  airQualityObject["sensorData"] = jsonString; 
  


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
/*
  This function send a message to server(url) and return response of server in payload and HTTP code of request 
  200 HTTP code is for success
*/
bool SendServerMessage(String url,String message,String& payload, int& httpCode) //Send Http server serialized JSON object and return payload and httpCode
{
       if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
 
         HTTPClient http;    //Declare object of class HTTPClient
     
         http.begin(url);      //Specify request destination
         Serial.print("[SERVER CONNECTION] CONNECTING TO "); Serial.println(url);
         http.addHeader( "Content-Type", "application/json");  //Specify content-type header
         Serial.print("[SERVER CONNECTION] SENDING SERVER MESSAGE : "); Serial.println(message);
         httpCode = http.POST(message);   //Send the request
         payload = http.getString();                  //Get the response payload
       
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

/*
  This function setups Hotspot of device
*/
void startHotSpot()
{

  // Start HoTSpoT so that Moblie can connect to this device
   WiFi.mode(WIFI_AP);
  WiFi.softAP(HOTSPOT_SSID, HOTSPOT_PASSWORD);
  Serial.println("Starting Hotspot . . .");

  // Show IP/Adress of this device.
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);

}

/*
  this function reset all settings 
  it start the hotspot and try to connect to Mobile Phone and receive data again
  it than writes all that data to EEPROM and restarts the device
*/
void resetSettings()
{
  startHotSpot();                         // Turn on HoTSpoT
  WiFiClient client = TCPGetClient();
  String rawData = recieveDataFromClient(client);
  
  parseAndWriteDataOnROM(rawData);
  delay(100);
  ESP.restart();
}

/*
  This function gets a TCP Client(Mobile phone)
  Note:
    The loop inside doesn't break untill connected device does'nt send some data 
*/
WiFiClient TCPGetClient()    //Returns TCP client
{
    // Start TCP Server
  server.begin();

  // Wait for client to connect
  Serial.println("[CLIENT STATUS] Waiting for TCPClient");
  while(true){

  WiFiClient  client = server.available();    
    if (client!=NULL){
      Serial.println("[CLIENT STATUS] TCP CLIENT CONNECTED");
      indicate_mobile_con.set(HIGH);    // After Connecting successfully there is no more blinking and indicator becomes stable
      return client; 
    }
    indicate_mobile_con.blink(100,5,LOW,0.5); //Blinking indicates that device is trying to connect to TCP Client
  }
}

/*
This function recieves data from mobile phone client
*/
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



/*
This function parse data received from mobile and write it to EEPROM
*/
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
  delay(500);
}
 /*
  This function updates the firmware of NodeMCU
  NOTE:
  It works fine but as written in the documentation of esp8266 if already existing firmware
  is installed through serial then the device should be reset manually first then OTA update
  will work fine.  
 */
void OTA_update_firmware()
{    
      indicate_firmware_update.blink(100,20,HIGH,0.7);
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


