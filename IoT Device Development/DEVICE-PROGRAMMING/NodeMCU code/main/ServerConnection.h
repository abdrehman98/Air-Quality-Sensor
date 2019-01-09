class ServerConnection{
private:
  String & deviceId;

  const char * URL = "192.168.1.25:8081";
  const char * dataRecievingSerivice = "/aqs/devices/packetrecord/1";
  const char * locationRecievingService = "/aqs/location";
  const char * errorRecievingService = "/aqs/errorlog";
  const char * updateCheckingService = "/aqs/devices/10/10/1";
  const char * upgradeConfimationService = "/aqs/devices/10/10/2";
public:

};
