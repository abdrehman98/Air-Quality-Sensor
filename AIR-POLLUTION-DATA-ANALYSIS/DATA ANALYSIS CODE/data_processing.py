import matplotlib.pyplot as plotter
import os
from datetime import datetime

PROGRAM_NAME = 'DOME JONG'
DATA_FOLDER_PATH = '../DATA/PAQI 11-10-2018'
DATE_FORMAT = '%Y-%m-%d %H:%M:%S'
# Order of columns in excel file.
INDEX_TIMESTAMP = 0
INDEX_TEMPERATURE = 1
INDEX_HUMIDITY = 2
INDEX_AQI = 3
INDEX_PM25 = 4
INDEX_CO2 = 5
INDEX_OUTDOOR_AQI = 6
INDEX_OUTDOOR_PM25 = 7


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
    raw_line = raw_line.plit(',')

    # Be careful: order of resultant list should be same as argument line
    # encode and append time stamp
    encoded_timestamp = encoded_timestamp(raw_line[INDEX_TIMESTAMP])
    encoded_row.append(encoded_timestamp)

    # encode remaining entries
    for n in range(1, len(raw_line)):
        encoded_entry = float(raw_line[n])
        encoded_row.append(encoded_entry)

    return encoded_row


def read_device_file(device_file_path):
    device_data = open(device_file_path, 'r').readlines()

    encoded_device_data = []
    for line in device_data:
        encoded_device_data.append(line)

    return encoded_device_data


def load_data(path):
    devices_list = os.listdir(path)
    devices_list.remove('BASIC-RESULT.txt')

    devices_data = []
    for device_name in devices_list:
        current_device_data = read_device_file(path + '/' + device_name)
        devices_data.append(current_device_data)

    return devices_list, devices_data


print '----------------------------------------------'
print 'Hello! ', PROGRAM_NAME, ' is here to help you.'
print '----------------------------------------------'
print 'Be patient ', PROGRAM_NAME, 'is loading files for you.'
devices_names_list, devices_data_list = load_data(DATA_FOLDER_PATH)
print PROGRAM_NAME, ' brought your files, Oooops they are very heavy.'
print_devices_names(devices_names_list)
