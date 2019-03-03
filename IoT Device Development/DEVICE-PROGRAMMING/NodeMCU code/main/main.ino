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

#include "debug.h"

#include "Device.h"
#include "SensorManager.h"
#include "Display.h"
#include "WIFI.h"

Device device;
Display display(device);
WIFI wifi;
SensorManager sensorManager;



void DELAY_SR(int t){

}
void setup() {
  debug("MAIN", "Entered in MAIN");
  display.welcome();
  wifi.begin(device);
  sensorManager.begin(device);

}

void loop() {
  DataPacket data = sensorManager.read();
  display.wifiStatus(wifi.getStatus());
  display.data(data);
}
