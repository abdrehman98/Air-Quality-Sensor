// ------------------------------// Internal/ External Import
#include <ESP8266WiFi.h>         // Wifi Liberary (Node MCU Core)
#include <ESP8266httpUpdate.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <string.h>
#include <WiFiServer.h>          // TCP/Server    (Node MCU Core)
#include <EEPROM.h>              // Accessing EEPROM (Arduino)
#include <ArduinoJson.h>
#include <StorageIO.h>
#include <SoftwareSerial.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <PMS.h>
#include <DHT.h>

#include "Device.h"
#include "Display.h"

Device device;
Display display(device);

/*
#include "MyWIFI.h"
#include "DeviceSensors.h"
#include "header/MobileConnection.h"
#include "ServerConnection.h"
*/

//#include "MobileConnection.cpp"
//#include "printLCD.cpp"
void setup() {
  Serial.begin(9600);
  delay(1000);
  display.welcome();

  //debug("MAIN", "Device Starting");
  //sensorBegin();
  //StorageIO rom;
  //DeviceInfo.begin(rom);
  //mobileConnectionInit();

  //wifiBegin(rom);
  //printBegin();
}

void loop() {
  //checkMobileConnectionRequest();
  //printData(readData());
  //delay(5 * 1000);
}
