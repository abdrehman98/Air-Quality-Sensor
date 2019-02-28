void printBegin() {
  lcd.begin(16,4);
  lcd.backlight();
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Device Starting");
  lcd.setCursor(0,1);
  lcd.print(String("ID:") + DeviceInfo.getDeviceId());
  delay(5000);
}

void printPhone() {
  lcd.clear();
  lcd.setCursor(5,0);
  lcd.print("Connect ");
  lcd.setCursor(1,1);
  lcd.print("your phone");
  lcd.setCursor(1,2);
  lcd.print("with love");
}

void printData(DataPacket data){
  lcd.setCursor(0,1);
  lcd.print("PM2.5: "); lcd.print((int) data.pmsData.PM_AE_UG_2_5);
  lcd.setCursor(0,2);
  lcd.print("Temperature: ");
  lcd.print(data.temperature);
  lcd.setCursor(0,3);
  lcd.print("Humidity: ");
  lcd.print(data.humidity);
}

void printWifiStatus(){
  lcd.clear();
  lcd.setCursor(0,0);
  if ( WiFi.status() == WL_CONNECTED ){
    lcd.print("WIFI Connected");
  }else{
    lcd.print("Dis Connected");
  }
}
