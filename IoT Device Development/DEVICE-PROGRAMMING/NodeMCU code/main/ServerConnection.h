
class ServerConnection{
private:
  String deviceId;
  char * host = "157.230.218.141";
  int port = 8081;

  const char * dataService = "/aqs/device/datarecord";
  const char * DEBUG_TAG = "SERVER_CONNECTION";
  HTTPClient http;

  int send(String data, String service){
    int ret = 0;
    String url = "http://";
    url += host;
    url += (":" + String(port));
    url += service;

    http.begin(url);
    debug(DEBUG_TAG, "[SERVER CONNECTION] CONNECTING TO ", url);

    http.addHeader( "Content-Type", "application/json");
    int STATUS_CODE = http.POST(data);
    debug(DEBUG_TAG, "[SERVER CONNECTION] SENDING SERVER MESSAGE : ", data);

    String payload = http.getString();
    debug(DEBUG_TAG, "STATUS-CODE:", STATUS_CODE);
    debug(DEBUG_TAG, "PAYLOAD:", payload);
    ret = true;
    http.end();
    return ret;
  }
public:
  void begin(Device & device){
    this->deviceId = device.getId();
  }
  int sendData(DataPacket & data){
    debug(DEBUG_TAG, "SENDING DATA TO SERVER");

    StaticJsonBuffer<200> jsonBuffer;
    JsonObject& jsonDataPacket  = jsonBuffer.createObject();

    debug(DEBUG_TAG, "PREPAIRING JSON");
    jsonDataPacket["deviceId"]    = deviceId;
    jsonDataPacket["temperature"] = data.temperature;
    jsonDataPacket["humidity"]    = data.humidity;
    jsonDataPacket["pm1"]         = data.pmsData.PM_AE_UG_1_0;
    jsonDataPacket["pm25"]        = data.pmsData.PM_AE_UG_2_5;
    jsonDataPacket["pm10"]        = data.pmsData.PM_AE_UG_10_0;
    jsonDataPacket["aqi"]         = data.aqi;
    String strJsonDataPacket;
    jsonDataPacket.printTo(strJsonDataPacket);

    debug(DEBUG_TAG, "PACKED_DATA_PACKET", strJsonDataPacket);
    return send(strJsonDataPacket, dataService);
  }

};
