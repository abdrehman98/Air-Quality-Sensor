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
8) pip (python package manager installed)
9) py-serial python library installed on laptop

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
4.1) Write python Script to read data
4.2) test in one sensor
4.3) Attach all sensors and run experiment
4.4) Save data for a day
5) MATLAB: error checking.
5.1) Read csv file
5.2) Encode timestamp
5.4) create basic line plot of pm2.5 SP of all devices
5.5) Save Figure in save /RESULT Diresctory 
5.6) seperate common data
5.7) Gussian filter of data
5.8) averaging and brining on common time stamp
6) Error check: Result compilation
6.1) Basic plot -> fig1.jpg --Too much noisy
6.3) Considering comman | averaging 1 minute -> fig2.jpg --Less noisy --Local trends
6.4) averaging 5 minute -> fig3.jpg --Less noisy --Local trends
6.5) averaging 10 minute -> fig4.jpg --Less noisy --Local trends
6.6) averaging 1 hour -> fig5.jpg --Local Trend loss
6.7) computing error using ecludian distance
6.7.1) 1 min sampling error -> fig6
6.7.2) 5 min sampling error -> fig7
6.7.3) 15 min sampling error -> fig8
6.7.3) 1 hour sampling error -> fig9
6.8) ERROR and SMOOTHNESS result compiled. 