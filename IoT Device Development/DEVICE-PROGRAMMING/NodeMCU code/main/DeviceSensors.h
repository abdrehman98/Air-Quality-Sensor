struct DataPacket{
  PMS::DATA2 pmsData;
  float temperature;
  float humidity;
};


DHT dht(DeviceInfo.DHTPIN, DeviceInfo.DHTTYPE);

SoftwareSerial pmsSerial(D5, D3);
PMS pms(pmsSerial);
PMS::DATA2 data;

void sensorBegin(){
  String DEBUG_TAG = "DATA_READING";

  // DHT Sensor intiallize
  dht.begin();

  // PMS3003 Sensor Object
  pmsSerial.begin(9600);
  debug(DEBUG_TAG, "PMS3003 Serial created");
}
DataPacket readData(){

  String DEBUG_TAG = "DATA_READING";
  DataPacket readData;

  // Delay before reading values
  delay(1000);

  // Reading DHT22 values
  readData.temperature = dht.readTemperature();
  readData.humidity = dht.readHumidity();
  debug(DEBUG_TAG, "DHT22 Temperature", readData.temperature);
  debug(DEBUG_TAG, "DHT22: Humidity", readData.humidity);
  if (isnan(readData.temperature) || isnan(readData.humidity)) {
    debug(DEBUG_TAG, "DHT22: Error! Failed to read data");
  }

  // Reading PMS3003 values
  while (! pms.read(data)) delay(15); // -- This Blocking call need to be improved
  debug(DEBUG_TAG, "PM 1.0 (ug/m3):", data.PM_AE_UG_1_0);
  debug(DEBUG_TAG, "PM 2.5 (ug/m3):", data.PM_AE_UG_2_5);
  debug(DEBUG_TAG, "PM 10 (ug/m3):", data.PM_AE_UG_10_0);
  readData.pmsData = data;

  return readData;
}
