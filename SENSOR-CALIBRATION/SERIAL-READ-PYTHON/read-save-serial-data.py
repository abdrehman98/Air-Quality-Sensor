import serial
import sys
import time


print('************************************************************')
print('*  A pthon code written for Air Quality Sensor Calibration *')
print('*        It will Read data from USB port and               *')
print('*      Display on Screen and Same on File as named         *')
print('*           exactly same as port name                      *')
print('************************************************************')

print('ENTER Serial-Porrt of Attched ARDUINO:')
serial_port = input()

f = open('..\\DATA\\SEP-27\\' + serial_port + ".csv", "a+")

ser = serial.Serial()
ser.baudrate = 9600
ser.port = serial_port
ser.open()

while True:
    data = ser.readline()
    data = data.decode('ascii')
    data = data.replace('\r\n','')
    data = data + "," + str(time.time()) + "\n"
    print(data)
    f.write(data)
