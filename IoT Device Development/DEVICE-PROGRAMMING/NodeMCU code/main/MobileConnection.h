String readBluetooth(SoftwareSerial &, String);
void connectToMobile();


volatile bool connectToMobileFlag = false;         // variable for reading the pushbutton status
void setMobileFlag() {  connectToMobileFlag = true;  }

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
  digitalWrite(DeviceInfo.BLUETOOTH_POWER_PIN, HIGH);
  SoftwareSerial blueTooth = SoftwareSerial(DeviceInfo.BLUETOOTH_TX, DeviceInfo.BLUETOOTH_RX);
  blueTooth.begin(DeviceInfo.BLUETOOTH_BAUD);
  delay(1000);

  String id = readBluetooth(blueTooth, "ID");
  blueTooth.println("ID");
  Serial.println(id);

  String ssid = readBluetooth(blueTooth, "SSID");
  blueTooth.println("SSID");
  Serial.println(ssid);

  String password = readBluetooth(blueTooth, "PASSWORD");
  blueTooth.println("PASSWORD");
  Serial.println(password);

  digitalWrite(DeviceInfo.BLUETOOTH_POWER_PIN, LOW);
}

String readBluetooth(SoftwareSerial & btSerial, String tag){
  String st;
  do {
    while(! btSerial.available()) delay(20);

    st = btSerial.readString();
    st.replace("\n", "");
    st.replace("\r", "");
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
