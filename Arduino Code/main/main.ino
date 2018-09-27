void setup(){
  Serial.begin(9600);
  pinMode(10,INPUT);
  pinMode(11,INPUT);
  delay(1000);
}
void loop(){
  long data1 = pulseIn(10, LOW);
  long data2 = pulseIn(11, LOW);
  Serial.println((String)data1 + "," + data2);
}
