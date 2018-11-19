import os

import GENERAL as PROGRAM
import PROCESSED_DATA_INDEX as INDEX


#####################################################
# Loading files for data Processing                 #
# 1) Read list of files                             #
# 2) Read their data                                #
# 2.1) Read Every devices data                      #
# 2.1.1) Encode every line of data                  #
# 3) Arrange devices names                          #
#####################################################


def encode_line(raw_line):
    # Break line on comma and separate entries
    encoded_row = []
    raw_line = raw_line.split(',')

    # Be careful: order of resultant list should be same as argument line
    # encode and append time stamp
    string_timestamp = raw_line[INDEX.TIMESTAMP]
    encoded_timestamp = PROGRAM.encode_timestamp(string_timestamp)
    encoded_row.append(encoded_timestamp)

    # encode remaining entries
    for n in range(1, len(raw_line)):
        try:
            encoded_entry = float(raw_line[n])
        except:
            encoded_entry = PROGRAM.MISSING_VALUE

        encoded_row.append(encoded_entry)

    return encoded_row


def read_device_file(device_file_path):
    encoded_device_data = []
    device_data = open(device_file_path, 'r').readlines()

    for line in device_data:
        encoded_device_data.append(encode_line(line))

    return encoded_device_data


def load_data(path):
    # Get the list of all devices
    # -Remove BASIC-RESULT file
    devices_names_list = os.listdir(path)
    devices_names_list.remove('BASIC-RESULT.txt')

    # Read device data
    devices_data = []
    for device_name in devices_names_list:
        current_device_data = read_device_file(path + '/' + device_name)
        devices_data.append(current_device_data)

    # Refine devices names
    # Remove .csv from device name
    for i in range(0, len(devices_names_list)):
        devices_names_list[i] = devices_names_list[i].replace('.csv', '')

    return devices_names_list, devices_data
