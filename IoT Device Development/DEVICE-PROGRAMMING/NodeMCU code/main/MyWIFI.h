const char * WIFI_DEBUG_TAG = "WIFI";
void wifiBegin(StorageIO & rom){
  debug(WIFI_DEBUG_TAG, "Turn off hardware (STA + SOftAP)");
  WiFi.softAPdisconnect(true);
  WiFi.disconnect(true);

  debug(WIFI_DEBUG_TAG, "Reading settings from EEPROM");
  char * ssid = rom.readNextString();
  char * password = rom.readNextString();
  debug(WIFI_DEBUG_TAG, "SSID", ssid);
  debug(WIFI_DEBUG_TAG, "PASSWORD", password);

  delay(300);
  WiFi.begin(ssid, password);
  debug(WIFI_DEBUG_TAG, "Turn on STA");

  delete ssid;
  delete password;
}
