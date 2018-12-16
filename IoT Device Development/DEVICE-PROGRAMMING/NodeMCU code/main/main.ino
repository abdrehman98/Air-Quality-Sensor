
// ------------------------------// Internal/ External Import
#include <ESP8266WiFi.h>         // Wifi Liberary (Node MCU Core)
#include <ESP8266httpUpdate.h>
#include <ESP8266HTTPClient.h>     
#include <WiFiClient.h>
#include <string.h> 
#include <WiFiServer.h>          // TCP/Server    (Node MCU Core)
#include <EEPROM.h>              // Accessing EEPROM (Arduino)
#include <ArduinoJson.h>
#include <StorageIO.h>           // To save SSID/Password/Latitude/Longitude https://github.com/Zeeshan-itu/StorageIO
#include <Indicator.h>
#include <SoftwareSerial.h>

#include "CONSTS.h"
#include "MobileConnection.h"

void indicateWifiStatus();
//--------------------------//Indicators
Indicator indicateMobileCon(BLUETOOTH_LED_PIN);
Indicator indicateWifiCon(WIFI_LED_PIN);
Indicator indicateDataSending(SERVER_LED_PIN);

//------------------------------------// Globalveriables
String deviceID;

void setup() {
  //------------------ Initializa Serial communication
  Serial.begin(9600);
  Serial.println("Starting...");

  pinMode(WIFI_LED_PIN, OUTPUT);
  pinMode(SERVER_LED_PIN, OUTPUT);

  mobileConnectionInit();
  WiFi.softAPdisconnect(true);
  WiFi.disconnect(true);
  StorageIO rom = StorageIO();
  char * id = rom.readNextString();
  char * ssid = rom.readNextString();
  char * password = rom.readNextString();
  Serial.println("DEVICE ID: " + (String) id);
  Serial.println("Connection Request:" + (String) ssid);
  Serial.println("Password:" + (String) password);
  WiFi.begin(ssid, password);

  deviceID = String(id);
  // Release memory
  delete id;
  delete ssid;
  delete password;
  // Wair until device is not connected
}

void loop() {
  if (connectToMobileFlag){
    connectToMobileFlag = false;
    connectToMobile();
  }
  indicateWifiStatus();
}

void indicateWifiStatus(){
  if ( WiFi.status() == WL_CONNECTED )
    digitalWrite(WIFI_LED_PIN, HIGH);
  else
    digitalWrite(WIFI_LED_PIN, LOW);
}
