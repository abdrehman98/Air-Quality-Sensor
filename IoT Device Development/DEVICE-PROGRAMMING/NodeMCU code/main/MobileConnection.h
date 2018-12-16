volatile bool connectToMobileFlag = false;         // variable for reading the pushbutton status
void setMobileFlag() {  connectToMobileFlag = true;  }

void mobileConnectionInit(){
    //--------------- Settings: Bluetooth  pin
    pinMode(BLUETOOTH_POWER_PIN, OUTPUT);   // -- intialize pin
    digitalWrite(BLUETOOTH_POWER_PIN, LOW); // -- Turn off bluetooth
    
    //--------------- Settings: Bluetooth turnon pin
    pinMode(BLUETOOTH_BUTTON_PIN, INPUT_PULLUP);
    attachInterrupt(digitalPinToInterrupt(BLUETOOTH_BUTTON_PIN),
        setMobileFlag, RISING);
}

void connectToMobile(){
    digitalWrite(BLUETOOTH_POWER_PIN, HIGH);
    SoftwareSerial blueTooth = SoftwareSerial(BLUETOOTH_TX, BLUETOOTH_RX);
    blueTooth.begin(BLUETOOTH_BAUD);
    blueTooth.println("Hello world");
    delay(1000); 
    while(! blueTooth.available()) delay(20);
    Serial.println("Hello" + blueTooth.readString());
    digitalWrite(BLUETOOTH_POWER_PIN, LOW);
}