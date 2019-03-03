struct DataPacket{
  PMS::DATA2 pmsData;
  float temperature;
  float humidity;
};

class SensorManager{
private:
  String DEBUG_TAG = "DATA_READING";
  DHT            * dht22;
  SoftwareSerial * pmsSerial;
  PMS            * pmsReader;

public:
  void begin(Device & device);
  DataPacket read();
};

void SensorManager::begin(Device & device) {
  dht22 = new DHT(device.DHT_PIN, device.DHT_TYPE);
  dht22->begin();

  pmsSerial = new SoftwareSerial(device.PMS_TX, device.PMS_RX);
  pmsSerial->begin(device.PMS_BAUD);
  pmsReader = new PMS(*pmsSerial);
  debug(DEBUG_TAG, " --Sensors ready-- ");
}

DataPacket SensorManager::read(){

  DataPacket readData;

  // Reading DHT22 values
  readData.temperature = dht22->readTemperature();
  readData.humidity = dht22->readHumidity();
  debug(DEBUG_TAG, "DHT22 Temperature", readData.temperature);
  debug(DEBUG_TAG, "DHT22: Humidity", readData.humidity);

  if (isnan(readData.temperature) || isnan(readData.humidity)) {
    debug(DEBUG_TAG, "DHT22: Error! Failed to read data");
  }

  // Reading PMS3003 values
  while (! pmsReader->read(readData.pmsData)) delay(15); // -- This Blocking call need to be improved
  debug(DEBUG_TAG, "PM 1.0 (ug/m3):", readData.pmsData.PM_AE_UG_1_0);
  debug(DEBUG_TAG, "PM 2.5 (ug/m3):", readData.pmsData.PM_AE_UG_2_5);
  debug(DEBUG_TAG, "PM 10 (ug/m3):", readData.pmsData.PM_AE_UG_10_0);

  return readData;
}
