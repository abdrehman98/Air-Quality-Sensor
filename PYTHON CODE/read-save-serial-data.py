import serial
import sys
import time
print('Please Enter serial port at which arduino is attached:')
serial_port = input()
f = open(serial_port + ".csv", "a+")
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
