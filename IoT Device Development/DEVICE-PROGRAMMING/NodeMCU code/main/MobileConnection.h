volatile bool connectToMobileFlag = false;         // variable for reading the pushbutton status
void setMobileFlag() {  connectToMobileFlag = true;  }

void mobileConnectionInit(){
    
    //--------------- Settings: Bluetooth turnon pin
    pinMode(BLUETOOTH_BUTTON_PIN, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(BLUETOOTH_BUTTON_PIN),
        setMobileFlag, RISING);
}

void connectToMobile(){
    SoftwareSerial blueTooth = SoftwareSerial(BLUETOOTH_TX, BLUETOOTH_RX);
    blueTooth.begin(BLUETOOTH_BAUD);
    blueTooth.println("Hello world");
    delay(1000);
}

String readBluetooth(SoftwareSerial & btSerial, String tag){
  do {
    while(! btSerial.available()) delay(20);
  } while(btSerial.readString() != "START_" + tag);

  String res_val = "";
  String read_avlue = "";
  while(read_val != "END_" + tag){
    res_val += read_val;
    while(! btSerial.available()) delay(20);
    String read_avlue = btSerial.readString();
  }
  
  return res_val;
}

