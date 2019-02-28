#include "DeviceInfo.h"

void mobileConnectionInit(){
  //--------------- Microcontroller turn-on pin
  pinMode(DeviceInfo.BLUETOOTH_POWER_PIN, OUTPUT);   // -- intialize pin
  digitalWrite(DeviceInfo.BLUETOOTH_POWER_PIN, LOW); // -- Turn off bluetooth

  //--------------- Settings: Bluetooth interrupt on pin
  pinMode(DeviceInfo.BLUETOOTH_BUTTON_PIN, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(DeviceInfo.BLUETOOTH_BUTTON_PIN),
    setMobileFlag, RISING);
}

bool checkMobileConnectionRequest(){
  if (connectToMobileFlag){
    connectToMobileFlag = false;
    connectToMobile();
    return true;
  }
  return false;
}

void connectToMobile(){
  printPhone();
  digitalWrite(DeviceInfo.BLUETOOTH_POWER_PIN, HIGH);
  SoftwareSerial blueTooth = SoftwareSerial(DeviceInfo.BLUETOOTH_TX, DeviceInfo.BLUETOOTH_RX);
  blueTooth.begin(DeviceInfo.BLUETOOTH_BAUD);
  delay(1000);

  blueTooth.println("SSID");
  String ssid = readBluetooth(blueTooth, "SSID");
  Serial.println(ssid);

  blueTooth.println("PASSWORD");
  String password = readBluetooth(blueTooth, "PASSWORD");
  Serial.println(password);

  blueTooth.println("LOCATION");
  String location = readBluetooth(blueTooth, "LOCATION");
  Serial.println(location);

  digitalWrite(DeviceInfo.BLUETOOTH_POWER_PIN, LOW);

  StorageIO rom;
  rom.reposition();

  rom.writeNextString(ssid);
  rom.writeNextString(password);

  rom.reposition();
  rom.readNextString();
  wifiBegin(rom);
}

String readBluetooth(SoftwareSerial & btSerial, String tag){
  String st;
  do {
    while(! btSerial.available()) delay(20);

    st = btSerial.readString();
    st.replace("\n", "");
    st.replace("\r", "");
    Serial.println(st);
  } while(st != "START_" + tag);

  String res_val = "";
  String read_val = "";

  do {
    res_val += read_val;
    while(! btSerial.available()) delay(20);
    read_val = btSerial.readString();
    read_val.replace("\n", "");
    read_val.replace("\r", "");
  }while(read_val != "END_" + tag);

  return res_val;
}
