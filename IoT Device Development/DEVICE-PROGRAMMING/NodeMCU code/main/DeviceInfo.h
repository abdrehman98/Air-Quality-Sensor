void debug(String DEBUG_TAG, String DEBUG_MESSAGE);
void debug(String DEBUG_TAG, String DEBUG_MESSAGE, String DEBUG_MESSAGE2);

class DeviceInfoClass{
private:
  char * deviceId;
public:
  // Basic device veriables
  const char * DEBUG_TAG = "DEVICE_INFO";
  const char * SENSOR_COMBINATION = "1";
  const char * CODE_VERSION = "1";

  // BLUE-TOOTH veriables
  const int BLUETOOTH_BAUD = 38400;
  const int BLUETOOTH_RX = D0;
  const int BLUETOOTH_TX = D4;
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
    debug(DEBUG_TAG, "Set device id");
    this->deviceId = rom.readNextString();

    debug(DEBUG_TAG, "Assigned device ID", this->deviceId);
  }
  char * getDeviceId(){
    return this->deviceId;
  }
} DeviceInfo;

void debug(String DEBUG_TAG, String DEBUG_MESSAGE){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE);
}

void debug(String DEBUG_TAG, String DEBUG_MESSAGE, String DEBUG_MESSAGE2){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE + " " + DEBUG_MESSAGE2);
}

void debug(String DEBUG_TAG, String DEBUG_MESSAGE, float DEBUG_MESSAGE2){
  Serial.println(DEBUG_TAG + ":>> " + DEBUG_MESSAGE + " " + String(DEBUG_MESSAGE2) );
}
