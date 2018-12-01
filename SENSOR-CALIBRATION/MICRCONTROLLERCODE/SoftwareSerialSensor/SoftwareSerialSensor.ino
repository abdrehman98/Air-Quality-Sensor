#include <SoftwareSerial.h>
#define RX 10
#define TX 11
SoftwareSerial pmsSerial(RX, TX);

void setup() {
  // put your setup code here, to run once:
  pmsSerial.begin(9600);
  pmsSerial.flush();
  delay(1000);
}

void loop() {
  // put your main code here, to run repeatedly:
  pmsSerial.println("abcdefghiblackjokerredjokejgnjljokermojicowmessy roladdo  a");
  delay(0.3 * 1000);
}
