
class Device{
private:
  char * id;
  char * ssid;
  char * password;
  const char * DEBUG_TAG = "DEVICE";
  StorageIO rom;
public:
  // Display
  const static int NUM_ROWS = 4;
  const static int NUM_COLS = 16;

  // DEVICE META
  const char * SENSOR_COMBINATION = "1";
  const char * CODE_VERSION = "1";

  // BLUE-TOOTH veriables
  const int BLUETOOTH_BAUD = 38400;
  // communication
  const int BLUETOOTH_RX = D0;
  const int BLUETOOTH_TX = D4;
  // switching
  const int BLUETOOTH_BUTTON_PIN = D7;
  const int BLUETOOTH_POWER_PIN = D8;

  // PMS-3003 Sensor pins
  const int PMS_RX = D3;
  const int PMS_TX = D5;

  // DHT-22 Sensor pins
  const int DHTPIN = D6;
  const int DHTTYPE = DHT22;

  // LCD pins
  // SDA
  // SCL

  bool begin(StorageIO & rom){
    debug(DEBUG_TAG, "Loading data from EEPROM");
    rom.reposition();
    id = rom.readNextString();
    ssid = rom.readNextString();
    password = rom.readNextString();
    debug(DEBUG_TAG, "ID", id);
    debug(DEBUG_TAG, "SSID", ssid);
    debug(DEBUG_TAG, "PASSWORD", password);
  }

  char * getId(){ return id;}
  char * getSsid(){ return ssid;}
  char * getPassword(){ return password;}

  void debug(String, String);
  void debug(String, String, String);
  void debug(String, String, float);
};

void Device::debug(String DEBUG_TAG, String DEBUG_MESSAGE){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE);
}

void Device::debug(String DEBUG_TAG, String DEBUG_MESSAGE, String DEBUG_MESSAGE2){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE + " " + DEBUG_MESSAGE2);
}

void Device::debug(String DEBUG_TAG, String DEBUG_MESSAGE, float DEBUG_MESSAGE2){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE + " " + String(DEBUG_MESSAGE2) );
}
