/* www.learningbuz.com */
/*Impport following Libraries*/
#include<Wire.h>
#include <LiquidCrystal_I2C.h>
#include<SoftwareSerial.h>
#include "DHT.h"
#define DHTPIN 5     // what digital pin we're connected to
#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321

//I2C pins declaration
LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE); 
#define RX 10
#define TX 11
#define DHT_PIN 9
DHT dht(DHTPIN, DHTTYPE);
#include "PMS.h"
SoftwareSerial pmsSerial(RX, TX);
PMS pms(pmsSerial);


void setup() {

  lcd.begin(16,2);//Defining 16 columns and 2 rows of lcd display
  lcd.backlight();//To Power ON the back light
  //lcd.backlight();// To Power OFF the back light
  Serial.begin(9600);   // GPIO1, GPIO3 (TX/RX pin on ESP-12E Development Board)
  dht.begin();
}

void loop() {
  PMS::DATA2 dat;

  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("T=");
  lcd.print("*C");
  lcd.print(" H=");
}
