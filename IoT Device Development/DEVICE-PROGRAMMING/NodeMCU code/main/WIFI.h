class WIFI{
private:
  const char * DEBUG_TAG = "WIFI";
  char * ssid;
  char * password;

public:
  void begin(Device & device);
  bool getStatus(){ return WiFi.status() == WL_CONNECTED; }

  void setSsid(char * name){ ssid = name;}
  void setPassword(char * key){ password = key;}
  void setSettings(Device & device);
  void startStation();
};

void WIFI::setSettings(Device & device){
  // Get the sttings from device
  debug(DEBUG_TAG, "Setting up values");
  ssid = device.getSsid();
  password = device.getPassword();
  debug(DEBUG_TAG, "SSID", ssid);
  debug(DEBUG_TAG, "PASSWORD", password);
}

void WIFI::startStation(){
  // Turn of hardware
  debug(DEBUG_TAG, "Turn off hardware", "(STA + SOftAP)");
  WiFi.softAPdisconnect(true);
  WiFi.disconnect(true);
  delay(1000);
  // Start wifi hardware
  WiFi.begin(ssid, password);
  debug(DEBUG_TAG, "STATION", "started");
}

void WIFI::begin(Device & device) {
  setSettings(device);
  startStation();
}
