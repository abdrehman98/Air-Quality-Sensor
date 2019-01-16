// ------------------------------// Internal/ External Import
#include <ESP8266WiFi.h>         // Wifi Liberary (Node MCU Core)
#include <ESP8266httpUpdate.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <string.h>
#include <WiFiServer.h>          // TCP/Server    (Node MCU Core)
#include <EEPROM.h>              // Accessing EEPROM (Arduino)
#include <ArduinoJson.h>
#include <SoftwareSerial.h>
#include <StorageIO.h>
#include "DeviceInfo.h"

#include "DeviceSensors.h"
#include "MobileConnection.h"
//#include "ServerConnection.h"
void indicateWifiStatus();


void setup() {
  Serial.begin(9600);
  delay(1000);
  Serial.println("Hello");

  //------------------ Initializa Serial communication
  /*

  Serial.println("Starting...");

  StorageIO rom;
  DeviceInfo.begin(rom.readNextString());
  pinMode(DeviceInfo.WIFI_LED_PIN, OUTPUT);
  //pinMode(SERVER_LED_PIN, OUTPUT);
  */
  mobileConnectionInit();
  /*
  WiFi.softAPdisconnect(true);
  WiFi.disconnect(true);
  delay(300);
  WiFi.begin(rom.readNextString(), rom.readNextString());
  Serial.printf("SSID: %s\n", WiFi.SSID().c_str());
  */
}

void loop() {
  checkMobileConnectionRequest();
  //readData();
  //indicateWifiStatus();
  //delay(5 * 1000);
}

void indicateWifiStatus(){
  if ( WiFi.status() == WL_CONNECTED ){
    digitalWrite(DeviceInfo.WIFI_LED_PIN, HIGH);
    Serial.println("connected");
  }else{
    digitalWrite(DeviceInfo.WIFI_LED_PIN, LOW);
    Serial.println("dis");
  }
}
