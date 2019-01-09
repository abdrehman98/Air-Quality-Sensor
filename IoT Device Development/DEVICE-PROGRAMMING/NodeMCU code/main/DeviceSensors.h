#include "PMS.h"
#include <SoftwareSerial.h>

struct DataPacket{
  PMS::DATA pmsData;
  float temperature;
  float humidity;
};

DataPacket readData(){
  const char * DEBUG_TAG = "DATA_READING:>> ";
  SoftwareSerial pmsSerial(DeviceInfo.PMS_TX, DeviceInfo.PMS_RX);
  PMS pms(pmsSerial);
  pmsSerial.begin(9600);
  Serial.println(String(DEBUG_TAG) + "SoftwareSerial created for PMS3003");
  delay(1000);

  DataPacket readData;
  PMS::DATA data;
  if (pms.read(data)){
    Serial.println(String(DEBUG_TAG) + "PM 1.0 (ug/m3): " + data.PM_AE_UG_1_0);
    Serial.println(String(DEBUG_TAG) + "PM 2.5 (ug/m3): " + data.PM_AE_UG_2_5);
    Serial.println(String(DEBUG_TAG) + "PM 10 (ug/m3): "  + data.PM_AE_UG_10_0);
  } else {
    Serial.println(String(DEBUG_TAG) + "Problem reading data from PMS3003");
  }
  readData.pmsData = data;
  readData.humidity = 0;
  readData.temperature = 0;
  return readData;
}
