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

void connectPhone();

#include "DeviceInfo.h"
#include "MyWIFI.h"
#include "DeviceSensors.h"
#include "MobileConnection.h"
#include "ServerConnection.h"
LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);

void lcdBegin();
void printLcdNormal(DataPacket data);
void connectPhone();


void setup() {
  Serial.begin(9600);
  delay(1000);


  debug("MAIN", "Device Starting");
  sensorBegin();
  StorageIO rom;
  DeviceInfo.begin(rom);
  mobileConnectionInit();

  wifiBegin(rom);
  lcdBegin();
}

void loop() {
  connectToMobile();
  checkMobileConnectionRequest();
  printLcdNormal(readData());
  delay(5 * 1000);
}



void lcdBegin() {
  lcd.begin(16,4);
  lcd.backlight();
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Device Starting");
  lcd.setCursor(0,1);
  lcd.print(String("ID:") + DeviceInfo.getDeviceId());
  delay(5000);
}
void connectPhone() {
  lcd.clear();
  lcd.setCursor(5,0);
  lcd.print("Connect ");
  lcd.setCursor(1,1);
  lcd.print("your phone");
  lcd.setCursor(1,2);
  lcd.print("with love");
}

void printLcdNormal(DataPacket data){
  lcd.clear();
  lcd.setCursor(0,0);
  if ( WiFi.status() == WL_CONNECTED ){
    lcd.print("WIFI Connected");
  }else{
    lcd.print("Dis Connected");
  }
  lcd.setCursor(0,1);
  lcd.print("PM2.5: ");
  lcd.print((int) data.pmsData.PM_AE_UG_2_5);
  lcd.setCursor(0,2);
  lcd.print("Temperature: ");
  lcd.print(data.temperature);
  lcd.setCursor(0,3);
  lcd.print("Humidity: ");
  lcd.print(data.humidity);
}
