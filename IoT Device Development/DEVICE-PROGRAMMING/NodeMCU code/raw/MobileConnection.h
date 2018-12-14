void mobileConnectionInit(){
    pinMode(BLUETOOTH_POWER_PIN, OUTPUT);
}

void connectToMobile(){
    digitalWrite(BLUETOOTH_POWER_PIN, HIGH);
    SoftwareSerial blueTooth = SoftwareSerial(BLUETOOTH_TX, BLUETOOTH_RX);
    blueTooth.begin(BLUETOOTH_BAUD);
    delay(1000); 
    Serial.println(blueTooth.readString());
    digitalWrite(BLUETOOTH_POWER_PIN, LOW);
}