#include <SoftwareSerial.h>
#define RX 10
#define TX 11

SoftwareSerial pmsSerial(RX, TX);
const int FIRST_BYTE = 0x42;
const int SECOND_BYTE = 0x4d;
const int PACKET_SIZE = 24;
const int NUMBER_OF_START_BYTES =2;
const int BUFFER_SIZE = PACKET_SIZE - NUMBER_OF_START_BYTES;
short buffer[BUFFER_SIZE];

void readPMS33(){
  unsigned long enter = millis();
  Serial.println("Going to read data");
  pmsSerial.flush();
  bool readData = false;
  while(! readData){
    if (pmsSerial.available())
      if (pmsSerial.read() == FIRST_BYTE)
        if (pmsSerial.available())
          if (pmsSerial.read() == SECOND_BYTE)
            readData = true;
  }
  unsigned long available = millis();
  for (int i = 0; i < BUFFER_SIZE; i++){
    while(! pmsSerial.available());
    buffer[i] = pmsSerial.read(); 
  }
  unsigned long end = millis();
  Serial.println("Mission complete");
  Serial.println(end - enter);
  Serial.println(end - available);
}

void setup() {
  // put your setup code here, to run once:
  pmsSerial.begin(9600);
  pmsSerial.listen();
  Serial.begin(9600);
  delay(1000);
}

void loop() {
  readPMS33();
  delay(3000);
  //int bufferCount = pmsSerial.available();
  //Serial.println(bufferCount);
  // put your main code here, to run repeatedly:
  //String str = "";
  //while( pmsSerial.available() > 0)
  //  str += (char) pmsSerial.read();
  //Serial.print(str);
  // delay(250);
}
