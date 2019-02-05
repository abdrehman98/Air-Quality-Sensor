import serial
import sys
import time


print('************************************************************')
print('*  A pthon code written for Air Quality Sensor Calibration *')
print('*        It will Read data from USB port and               *')
print('*      Display on Screen and Same on File as named         *')
print('*            as Sensor id                                  *')
print('************************************************************')

BASIC_DATA_FOLDER_PATH = '../EXTRECTED-DATA/'
DATA_VERSION = 'DEC-6/'
serial_port = input('ENTER Serial-Porrt:')
sensor_id = input('Enter Sensor-ID')


f = open(sensor_id + ".csv", "a+")

ser = serial.Serial()
ser.baudrate = 9600
ser.port = serial_port
ser.open()

while True:
    data = ser.readline()
    data = data.decode('ascii')
    data = data.replace('\r\n', '')
    data = data + "," + str(time.time()) + "\n"
    print(data, end='')
    f.write(data)
