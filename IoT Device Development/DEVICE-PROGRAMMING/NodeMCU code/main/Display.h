class Display{
private:
  LiquidCrystal_I2C * lcd;
  String deviceId;
public:
  Display(Device & device){
    lcd = new LiquidCrystal_I2C(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);
    deviceId = device.getId();
    lcd->begin(device.NUM_COLS, device.NUM_ROWS);
  }
  void welcome();
  void phone();
  void wifiStatus(bool);
  //void data(DataPacket);
};

void Display::welcome(){
  // Set lcd size

  lcd->backlight();  // turn on background light
  lcd->clear();      // remove all previous content

  lcd->setCursor(0,0);
  lcd->print("Device Starting");
  delay(100);
  lcd->setCursor(0, 1);
  lcd->print("ID:");// + deviceId);

  delay(5000);
}
