import serial
import sys
import time
import os

print('************************************************************')
print('*  A pthon code written for Air Quality Sensor Calibration *')
print('*        It will Read data from USB port and               *')
print('*      Display on Screen and Same on File as named         *')
print('*            as Sensor id                                  *')
print('************************************************************')

serial_port = input('ENTER Serial-Port:')
sensor_id = input('Enter Sensor-ID')
data_version = input("Enter data version [Month-Day]:>>")

BASIC_DATA_FOLDER_PATH = '../DATA+RESULTS/'
folder = BASIC_DATA_FOLDER_PATH + data_version
os.makedirs(folder, exist_ok=True)

f = open(folder + sensor_id + ".csv", "a+")

ser = serial.Serial()
ser.baudrate = 9600
ser.port = serial_port
ser.open()

while True:
    data = ser.readline()
    data = data.decode('ascii')
    data = data.replace('\r\n', '')
    data = data + "," + str(time.time())
    print(data)
    f.write(data + "\n")
