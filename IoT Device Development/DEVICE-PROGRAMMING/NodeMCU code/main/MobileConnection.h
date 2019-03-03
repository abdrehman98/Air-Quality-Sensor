class MobileConnection{
private:
  int btBaud;
  int btRx;
  int btTx;
  int btInput;
  int button;

  static volatile bool connectionRequest;
  static void M_ISR(){
    connectionRequest = true;
  }
public:
  void begin(Device & device);
  bool getRequestStatus();
  void connect();
};
void begin(Device & device){

  btBaud  = device.BLUETOOTH_BAUD;
  btRx    = device.BLUETOOTH_RX;
  btTx    = device.BLUETOOTH_TX;
  btInput = device.BLUETOOTH_POWER_PIN;
  button  = device.BLUETOOTH_BUTTON_PIN;

  pinMode(btInput, OUTPUT);   // -- intialize pin
  digitalWrite(btInput, LOW); // -- Turn off bluetooth

  pinMode(button, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(button),
    M_ISR, RISING);
}

bool MobileConnection::getRequestStatus(){
  return connectionRequest;
}

void MobileConnection::connect(){
  digitalWrite(btInput, HIGH);

  SoftwareSerial blueTooth = SoftwareSerial(btTx, btRx);
  blueTooth.begin(btBaud);

  while(! btSerial.available()) delay(20);
  st = btSerial.readString();

  st.replace("\n", "");
  st.replace("\r", "");
  debug(DEBUG_TAG, "Data recieved", st);

  delay(500);

  digitalWrite(btInput, HIGH);
}
