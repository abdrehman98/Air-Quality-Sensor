volatile bool connectToMobileFlag = false;         // variable for reading the pushbutton status
void setMobileFlag() {
  connectToMobileFlag = true;
}
void mobileConnectionInit();
bool checkMobileConnectionRequest();
void connectToMobile();
String readBluetooth(SoftwareSerial&, String);
