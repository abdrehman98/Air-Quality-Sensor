from datetime import datetime

import PROCESSED_DATA_INDEX as INDEX

###########################################
##
# Constant variable section
##


PROGRAM_NAME = 'DOME JONG'
DATA_FOLDER_PATH = '../DATA/PAQI 11-10-2018'
DATE_FORMAT = '%Y-%m-%d %H:%M:%S'
ORDER_FORWARD = 0
ORDER_REVERSE = 1


def get_column(index, grid):
    return [row[index] for row in grid]


def print_devices_names(devices_names):
    for n in range(0, len(devices_names)):
        print n, '] ', devices_names[n]


def encode_timestamp(timestamp_string):
    return datetime.strptime(timestamp_string, DATE_FORMAT)


##
# Check if a list is sorted according
# to given order
def is_sorted(data_list, order):
    if order == ORDER_REVERSE:
        for i in range(0, len(data_list) - 1):
            if data_list[i] < data_list[i + 1]:
                return False
        return True
    else:
        for i in range(0, len(data_list) - 1):
            if data_list[i] > data_list[i + 1]:
                return False
        return True


##
# Generate vector of data and time
# with an given increment of delta
def datetime_range(start, end, delta):
    time_vector = []
    current = start

    if start >= end:
        while current >= end:
            time_vector.append(current)
            current += delta
    else:
        while current <= end:
            time_vector.append(current)
            current += delta

    return time_vector


def find_min_vector(devices_data_list):
    min_time = []
    for device_data in devices_data_list:
        time = get_column(INDEX.TIMESTAMP, device_data)
        min_time.append(min(time))
    return min_time


def find_max_vector(devices_data_list):
    max_time = []
    for device_data in devices_data_list:
        time = get_column(INDEX.TIMESTAMP, device_data)
        max_time.append(max(time))
    return max_time
