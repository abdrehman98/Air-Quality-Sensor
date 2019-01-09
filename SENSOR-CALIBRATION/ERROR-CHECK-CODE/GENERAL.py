from datetime import datetime


###########################################
##
# Constant variable section
##

# --------------[1-3 hour, 3-24 hour, 1-10 days, 10]
DAY = 24
YEAR = DAY * 365
TIME_GAP_HIST_CATEGORIES = ['1 - 4 hour', '1 Day', '15 Days', 'Very Long interval']
TIME_GAP_HIST = [1, 4, DAY, 15 * DAY, 3 * YEAR]
PROGRAM_NAME = 'DOME JONG'

DEFAULT_FIG = 1
SAVE_FIG = 0
SHOW_FIG = 1
AVAILABLE = 'available'
MISSING = 'missing'
RESULT_FOLDER_BASIC_PATH = '../RESULT/'
BASIC_RESULT_FILE = 'BASIC - RESULT.txt'
RESULT_GAP_FOLDER_RELATIVE = 'DATA-GAP/'
RESULT_GAT_EXTENSION = '-gap.png'
RESULT_SPARSITY_EXTENSION = '-spr.png'
DATA_FOLDER_PATH = '../DATA/PAQI 11-10-2018'
DATE_FORMAT = '%Y-%m-%d %H:%M:%S'
ORDER_FORWARD = 0
ORDER_REVERSE = 1
MISSING_VALUE = -55555
LANDSCAPE_IMAGE = (18, 6)


def get_column(index, grid):
    return [row[index] for row in grid]


def print_devices_names(devices_names):
    for n in range(0, len(devices_names)):
        print(n, '] ', devices_names[n])


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


#####################
# Show or save plot #
#####################
def output_plot(plotter, path, operation, clf=True, title_name=''):
    if operation == SAVE_FIG:
        print('Saving: >>', title_name)
        plotter.savefig(path)
    elif operation == SHOW_FIG:
        print('Showing: >>', title_name)
        plotter.show(block=False)
        plotter.pause(0.33)
        input()

    if clf:
        plotter.clf()
