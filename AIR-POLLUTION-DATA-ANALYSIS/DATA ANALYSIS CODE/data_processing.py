

import os
from datetime import datetime
from datetime import timedelta
import matplotlib.pyplot as plotter

###########################################
##
# Constant variable section
##

PROGRAM_NAME = 'DOME JONG'
DATA_FOLDER_PATH = '../DATA/PAQI 11-10-2018'
DATE_FORMAT = '%Y-%m-%d %H:%M:%S'

##
# Columns Tags
# Tag name vs column number
INDEX_TIMESTAMP = 0
INDEX_TEMPERATURE = 1
INDEX_HUMIDITY = 2
INDEX_AQI = 3
INDEX_PM25 = 4
INDEX_CO2 = 5
INDEX_OUTDOOR_AQI = 6
INDEX_OUTDOOR_PM25 = 7


def print_device_time_info(time_column):

    # Find minimum and maximum date
    min_time = min(time_column)
    max_time = max(time_column)

    # print device starting and Ending time
    print 'Device starting time: ', min_time
    print 'Last retrieved Data:  ', max_time

    # Find number of hours passed after starting device
    time_d = max_time - min_time   # type: timedelta
    ideal_points = time_d.total_seconds() / (60 * 60)
    print 'Estimated pints:              ', ideal_points

    # Find number of available samples and print
    available_points = len(time_column)
    print 'Total containing data points: ', available_points

    # Find data lose total and percentage
    data_lose = ideal_points - available_points
    percentage_data_loss = (1 - available_points / ideal_points) * 100.0
    print 'Total data lose:              ', data_lose, '(Data points)'
    print 'Percent data lose:              ', percentage_data_loss, '%'


def print_all_devices_time_info(devices_mata):

    device_name, device_data = devices_mata

    for n in range(len(device_name)):
        print '----------------------------------------------'
        print device_name[n]
        print_device_time_info(get_column(INDEX_TIMESTAMP, device_data[n]))


def get_column(index, grid):
    return [row[index] for row in grid]


def print_devices_names(devices_names):
    for n in range(0, len(devices_names)):
        print n, '] ', devices_names[n]


def encode_timestamp(timestamp_string):
    return datetime.strptime(timestamp_string, DATE_FORMAT)


def encode_line(raw_line):

    # Break line on comma and separate entries
    encoded_row = []
    raw_line = raw_line.split(',')

    # Be careful: order of resultant list should be same as argument line
    # encode and append time stamp
    string_timestamp = raw_line[INDEX_TIMESTAMP]

    encoded_timestamp = encode_timestamp(string_timestamp)
    encoded_row.append(encoded_timestamp)
    # encode remaining entries
    for n in range(1, len(raw_line)):
        try:
            encoded_entry = float(raw_line[n])
        except:
            encoded_entry = -1

        encoded_row.append(encoded_entry)

    return encoded_row


def read_device_file(device_file_path):
    encoded_device_data = []
    device_data = open(device_file_path, 'r').readlines()

    for line in device_data:
        encoded_device_data.append(encode_line(line))

    return encoded_device_data


def load_data(path):
    devices_list = os.listdir(path)
    devices_list.remove('BASIC-RESULT.txt')

    devices_data = []
    for device_name in devices_list:
        current_device_data = read_device_file(path + '/' + device_name)
        devices_data.append(current_device_data)

    return devices_list, devices_data


##
# Making main the entry
# to avoid making global variables

def main():
    print '\n', '\n'
    print '----------------------------------------------'
    print 'Hello! ', PROGRAM_NAME, ' is here to help you.'
    print '----------------------------------------------'
    print 'Be patient ', PROGRAM_NAME, 'is loading files for you.'
    devices_names_list, devices_data_list = load_data(DATA_FOLDER_PATH)
    print PROGRAM_NAME, ' brought your files, Oooops they are very heavy.'

    # print_devices_names(devices_names_list)
    # device_1 = devices_data_list[0]
    # device1_timestamp = get_column(INDEX_TIMESTAMP, device_1)
    # device1_AQI =
    # print_device_time_info(device1_timestamp)
    print_all_devices_time_info((devices_names_list, devices_data_list))
    # plotter.plot(get_column(INDEX_TIMESTAMP, device_1), get_column(INDEX_TEMPERATURE, device_1))
    # print (get_column(INDEX_TIMESTAMP, device_1))
    # plotter.show()


main()
