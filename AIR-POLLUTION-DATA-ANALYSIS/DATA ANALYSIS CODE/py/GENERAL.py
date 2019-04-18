from datetime import datetime
import os


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


def get_data_between(data, mints, maxts, index=0):
    required_data = []

    num_col = len(data)
    num_row = len(data[0])

    for i in range(num_col):
        required_data.append([])

    for r in range(num_row):
        ts = data[index][r]
        if mints <= ts <= maxts:
            for c in range(num_col):
                required_data[c].append(data[c][r])

    return required_data


# Print iterations progress
def progress(itr, total, pre='', suf='', decimals=2, length=100, fill='█'):
    """
    Call in a loop to create terminal progress bar
    @params:
        iteration   - Required  : current iteration (Int)
        total       - Required  : total iterations (Int)
        prefix      - Optional  : prefix string (Str)
        suffix      - Optional  : suffix string (Str)
        decimals    - Optional  : positive number of decimals in percent complete (Int)
        length      - Optional  : character length of bar (Int)
        fill        - Optional  : bar fill character (Str)
    """
    percent = ("{0:." + str(decimals) + "f}").format(100 * (itr / float(total)))
    filledLength = int(length * itr // total)
    bar = fill * filledLength + '-' * (length - filledLength)
    print('\r%s |%s| %s%% %s' % (pre, bar, percent, suf), end ='\r')
    # Print New Line on Complete
    if itr == total:
        print()
