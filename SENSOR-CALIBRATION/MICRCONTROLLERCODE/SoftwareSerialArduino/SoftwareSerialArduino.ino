#include <SoftwareSerial.h>
#define RX 10
#define TX 11
SoftwareSerial pmsSerial(RX, TX);

void setup() {
  // put your setup code here, to run once:
  pmsSerial.begin(9600);
  pmsSerial.flush();
  pmsSerial.listen();
  Serial.begin(9600);
  delay(1000);
}

void loop() {
  int bufferCount = pmsSerial.available();
  Serial.println(bufferCount);
  // put your main code here, to run repeatedly:
  //String str = "";
  //while( pmsSerial.available() > 0)
  //  str += (char) pmsSerial.read();
  //Serial.print(str);
  delay(250);
}
