class DeviceInfoClass{
private:
  char * deviceId;
public:
  const char * DEBUG_TAG = "DEVICE_INFO:>> ";
  const char * SENSOR_COMBINATION = "1";
  const char * CODE_VERSION = "1";
  const int BLUETOOTH_BAUD = 38400;
  const int BLUETOOTH_RX = D0;
  const int BLUETOOTH_TX = D4;
  const int PMS_RX = D2;
  const int PMS_TX = D8;
  const int BLUETOOTH_BUTTON_PIN = D7;
  const int BLUETOOTH_POWER_PIN = D8;
  const int WIFI_LED_PIN = D6;
  const int SERVER_LED_PIN = D7;

  // This Function could be optemised by directly reading data from EEPROM
  bool begin(char * id){
    this->deviceId = id;
    Serial.println( String(DEBUG_TAG)  +
                  "Assigned device ID: " +
                  this->deviceId);
  }
  char * getDeviceId(){
    return this->deviceId;
  }
} DeviceInfo;
