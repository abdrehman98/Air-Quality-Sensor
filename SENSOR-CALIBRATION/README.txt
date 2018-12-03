Experiment purpose:
testing PMS3003 plantower sensors accuracy.

Requirements:
1) Arduino IDE
2) Arduino uno with serial cable x 3
3) PMS3003 sensor with cable
4) LAPTOP with 3 (USB 2.0 port of above)
5) male to male jumbers
6) Python 3.6 installed on laptop
7) CMD and python added to path veriable

Assumption:
used library works properly

Experiment procedure:
1) testing serial
1.1) Write serial communication code.
1.2) One by one upload on each arduino board.
1.3) use different cable and Serial port each time.
1.4) Experiment successful got expected results
2) testing software serial
2.1) Creating Dummy sensor by making arduino software serial tramsmitter.
3) Sensor Hookup
3.1) Read number of  bytes available at SoftwareSerial
3.2) Read data using softwareserial
3.3) Experiment failed 5 hours long
3.4) PMS Library installed
3.5) Read data using SoftwareSerial and PMS library
https://github.com/fu-hsi/PMS
3.6) Structure Serial data
4) Write python script to read data from USB and print on console
5) Improve script to save in files
6) read files and compare error