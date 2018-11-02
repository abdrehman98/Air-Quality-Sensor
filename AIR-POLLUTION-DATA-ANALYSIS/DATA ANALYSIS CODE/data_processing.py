

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


def is_sorted(data_list):
    for i in range(0, len(data_list) - 1):
        if data_list[i] < data_list [i + 1]:
            return False
    return True


def datetime_range(start, end, delta):
    time_vector = []
    current = start
    while current >= end:
        time_vector.append(current)
        current += delta
    return time_vector


def plot_data_gap(devices_names_list, devices_data_list):

    ##
    # We have to find when the first device was deployed
    # and at what time instance last data was received
    # Method:
    # 1) Find min timestamp of every device data
    # 2) Find maximum timestamp of every device data
    # 3) find min of resultant min and max of resultant max

    # Lists to store min/max of devices
    min_time = []
    max_time = []

    # find min and max of every device
    # Run loop for every device
    for device_data in devices_data_list:
        time = get_column(INDEX_TIMESTAMP, device_data)
        min_time.append(min(time))
        max_time.append(max(time))

    # Find when First device was deployed and last data was updated
    first_device_deploy_time = min(min_time)
    last_data_update = max(max_time)

    ##
    # Compute data availability grid
    # Procedure
    # 1) find universal vector of time
    # 1.1) vector will start from max-time and end at min-time
    # 1.1) Time difference will be one hour. (Device sampling 1 sample/hour)
    # 2) Compute sparsity vectors:
    # 2.1) it will contain 0 if data is not there and h if value is present.
    # 2.2) h is number of device

    # computing universal time vector
    universal_ts_vector = datetime_range(last_data_update,
                                         first_device_deploy_time,
                                         timedelta(hours=-1))

    # computing sparsity grid
    data_availability_grid = []
    for k in range(0, len(devices_data_list)):
        device_data = devices_data_list[k]
        device_time = get_column(INDEX_TIMESTAMP, device_data)
        # compute the step size to indicate data availability
        h = k + 1
        n = 0

        len_time = len(device_time)
        current_device_data_availability_vector = []
        for i in universal_ts_vector:
            if n >= len_time:
                current_device_data_availability_vector.append(0)
            elif device_time[n] != i:
                current_device_data_availability_vector.append(0)
            else:
                n = n + 1
                current_device_data_availability_vector.append(h)
        data_availability_grid.append(current_device_data_availability_vector)

    ##
    # Plotting data:
    for sparsity_vector in data_availability_grid:
        plotter.clf()
        plotter.plot(universal_ts_vector, sparsity_vector)
    plotter.show()


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
    # Hello Remarks!
    print '\n', '\n'
    print '----------------------------------------------'
    print 'Hello! ', PROGRAM_NAME, ' is here to help you.'
    print '----------------------------------------------'
    print 'Be patient ', PROGRAM_NAME, 'is loading files for you.'

    # Load files
    devices_names_list, devices_data_list = load_data(DATA_FOLDER_PATH)
    print PROGRAM_NAME, ' brought your files, Oooops they are very heavy.'

    plot_data_gap(devices_names_list, devices_data_list)


main()
