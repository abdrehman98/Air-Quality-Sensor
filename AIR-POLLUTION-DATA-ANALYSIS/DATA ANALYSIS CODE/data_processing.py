import os
import data_gap

import GENERAL as PROGRAM
import PROCESSED_DATA_INDEX as INDEX


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
    # Hello Remarks!
    print '------>', 'Hello! ', PROGRAM.PROGRAM_NAME, ' is here to help you.'
    print '----->', 'Be patient ', PROGRAM.PROGRAM_NAME, 'is loading files for you.'

    # Load files
    devices_names_list, devices_data_list = load_data(PROGRAM.DATA_FOLDER_PATH)
    print PROGRAM.PROGRAM_NAME, ' brought your files, Oooops they are very heavy.'

    data_gap.print_all_devices_time_info(devices_names_list, devices_data_list)
    data_gap.plot_data_gap(devices_names_list, devices_data_list)


main()
