#include "PMS.h"
#include <SoftwareSerial.h>
#define RX 10
#define TX 11

SoftwareSerial pmsSerial(RX, TX);

PMS pms(pmsSerial);
PMS::DATA2 data;

void setup(){
  pmsSerial.begin(9600);   // GPIO1, GPIO3 (TX/RX pin on ESP-12E Development Board)
  Serial.begin(9600);  // GPIO2 (D4 pin on ESP-12E Development Board)
}

void loop(){
  if (pms.read(data)){
    String encoded = (String) data.PM_SP_UG_1_0 + "," +
                              data.PM_SP_UG_2_5 + "," +
                              data.PM_SP_UG_10_0 + "," +
                              data.PM_AE_UG_1_0 + "," +
                              data.PM_AE_UG_2_5 + "," +
                              data.PM_AE_UG_10_0;

    Serial.println(encoded);
  }
}
