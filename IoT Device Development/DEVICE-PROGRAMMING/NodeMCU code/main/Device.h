class Device{

public:
  //---------------- Display
  //---------------- Size of LCD
  const static int NUM_ROWS = 4;
  const static int NUM_COLS = 16;

  //----------------- DEVICE META
  //----------------- Sensors info
  const char * SENSOR_COMBINATION = "1";
  const char * CODE_VERSION = "1";

  //------------------ BLUE-TOOTH veriables
  const static int BLUETOOTH_BAUD = 38400;
  // communication
  const static int BLUETOOTH_RX = D0;
  const static int BLUETOOTH_TX = D4;
  // switching
  const static int BLUETOOTH_BUTTON_PIN = D7;
  const static int BLUETOOTH_POWER_PIN = D8;

  // PMS-3003 Sensor pins
  const static int PMS_RX = D3;
  const static int PMS_TX = D5;
  const static int PMS_BAUD = 9600;
  // DHT-22 Sensor pins
  const static int DHT_PIN = D6;
  const static int DHT_TYPE = DHT22;

  // LCD pins
  // SDA
  // SCL

  /**********************************************
  *************** Functions will start **********
  ***********************************************/
  Device(){
    Serial.begin(9600);
    delay(500);

    Serial.println();
    Serial.println("***********************");
    Serial.println("_____      ______ _____");
    Serial.println("|    \\       |    |   |");
    Serial.println("|     \\      |    |___|");
    Serial.println("|______\\     |    |\\");
    Serial.println("|       \\    |    | \\");
    Serial.println("|        \\___|___ |  \\");
    Serial.println("CAST: An dream of clean air");

    debug(DEBUG_TAG, "Loading data from EEPROM");

    StorageIO rom;
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

private:
  char * id;
  char * ssid;
  char * password;
  const char * DEBUG_TAG = "DEVICE";
};
