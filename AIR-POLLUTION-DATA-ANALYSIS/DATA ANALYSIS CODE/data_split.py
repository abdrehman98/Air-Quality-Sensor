import tags as COLUMN_TAG
import os
from datetime import datetime
import GENERAL as PROGRAM


BASIC_PATH = '../DATA/PAQI 11-10-2018'
path_to_file = BASIC_PATH + '.csv'
PATH_TO_BASIC_RES = BASIC_PATH + '/BASIC-RESULT.txt'
SEPARATOR = '<>@<>'
DATE_FORMAT = '%Y-%m-%d %H:%M:%S'


def valid_timestamp(timestamp_string):
    try:
        datetime.strptime(timestamp_string, DATE_FORMAT)
        return True
    except:
        return False


def str_is_float(string):
    try:
        float(string)
        return True
    except:
        return False


def get_column(index, grid):
    return [row[index] for row in grid]


def read_file(file_path):
    excel_file = open(file_path, 'r')
    file_content = excel_file.readlines()
    print('Total number of lines read from file: ', len(file_content))

    split_lines = []
    for line in file_content:
        line = line.replace('\n', '')
        line = line.replace(', B', ' B')
        line = line.split(',')
        split_lines.append(line)

    print('Number of columns in File', len(split_lines[0]))
    return split_lines[0], split_lines[1:len(split_lines)]


def null_fill(column, tag):
    for n in range(0, len(column)):

        if '' == column[n][tag.index(COLUMN_TAG.OUTDOOR_PM25)]:
            column[n][tag.index(COLUMN_TAG.OUTDOOR_PM25)] = PROGRAM.MISSING_VALUE

        if '' == column[n][tag.index(COLUMN_TAG.OUTDOOR_AQI)]:
            column[n][tag.index(COLUMN_TAG.OUTDOOR_AQI)] = PROGRAM.MISSING_VALUE

        if '' == column[n][tag.index(COLUMN_TAG.KEY)]:
            column[n][tag.index(COLUMN_TAG.KEY)] = 'null'

        if '' == column[n][tag.index(COLUMN_TAG.CITY)]:
            print('Critical error city name null at line:', n + 1)

        if '' == column[n][tag.index(COLUMN_TAG.AREA)]:
            print('Critical error area null at line:', n + 1)

        if not valid_timestamp(column[n][tag.index(COLUMN_TAG.TIME_STAMP)]):
            print('Error: Invalid Time Stamp:', n + 1, '] ', column[n][tag.index(COLUMN_TAG.TIME_STAMP)])

    return column


def get_cities_and_their_devices(data, tag):
    city_index = tag.index(COLUMN_TAG.CITY)
    unique_cities = set([row[city_index] for row in data])
    list_of_cities_and_devices = []
    for selected_city in unique_cities:
        devices_list = []

        for topple in data:
            if topple[tag.index(COLUMN_TAG.CITY)] == selected_city:
                devices_list.append(topple[tag.index(COLUMN_TAG.AREA)])

        list_of_cities_and_devices.append((selected_city, set(devices_list)))

    return list_of_cities_and_devices


def save_basic_info(data):
    try:
        os.makedirs(BASIC_PATH)
        print('Creating folder ', BASIC_PATH)
    except:
        print('Folder already exists', BASIC_PATH)

    basic_res_file = open(PROGRAM.RESULT_FOLDER_BASIC_PATH +
                          PROGRAM.BASIC_RESULT_FILE, "w+")
    basic_res_file.write('Number of cities:' + str(len(data)) + '\n')
    for i in range(0, len(data)):
        city, devices = data[i]
        basic_res_file.write(str(i) + '] ' + city + ': contains ' + str(len(devices)) + ' number of devices.\n')

    basic_res_file.write('---------------------------------------------------------------' + '\n')

    structured_data = []
    for topple in data:
        city, devices = topple
        basic_res_file.write(city + ':\n')

        for i in range(0, len(devices)):
            device = list(devices)[i]
            basic_res_file.write(str(i) + '] ' + device + '.\n')
            structured_data.append([city, device])

    basic_res_file.close()
    return structured_data


def save_split_data(list_city_device, data, tag):

    # Open a file to Write some basic info
    basic_res_file = open(PROGRAM.RESULT_FOLDER_BASIC_PATH +
                          PROGRAM.BASIC_RESULT_FILE, "a+")
    basic_res_file.write(SEPARATOR + '\n')

    # Choose one device (CITY NAME, DEVICE_FULL_DATA) form total data
    for city_device_topple in list_city_device:

        # Separate city names list and devices list
        city = city_device_topple[0]
        device = city_device_topple[1]  # type: list

        current_device_data = []
        for data_topple in data:  # type: list
            if city == data_topple[tag.index(COLUMN_TAG.CITY)] and \
                    device == data_topple[tag.index(COLUMN_TAG.AREA)]:
                current_device_data.append(data_topple)

        current_device_key = set(get_column(tag.index(COLUMN_TAG.KEY), current_device_data))
        current_device_lat = set(get_column(tag.index(COLUMN_TAG.LATITUDE), current_device_data))
        current_device_lon = set(get_column(tag.index(COLUMN_TAG.LONGITUDE), current_device_data))
        current_device_install_time = set(get_column(tag.index(COLUMN_TAG.SETUP_TIME), current_device_data))

        file_name = city + ' - ' + device
        string_to_write = file_name + SEPARATOR + str(current_device_key) + SEPARATOR
        string_to_write = string_to_write + '(' + str(current_device_lat) + ',' + str(current_device_lon) + ')'
        string_to_write = string_to_write + SEPARATOR
        string_to_write = string_to_write + str(current_device_install_time) + SEPARATOR
        string_to_write = string_to_write + str(len(current_device_data))
        basic_res_file.write(string_to_write + '\n')

        current_device_data_file = open(BASIC_PATH + '/' + file_name + '.csv', "w+")
        for topple in current_device_data:
            timestamp_index = tag.index(COLUMN_TAG.TIME_STAMP)
            temperature_index = tag.index(COLUMN_TAG.TEMPERATURE)
            humidity_index = tag.index(COLUMN_TAG.HUMIDITY)
            aqi_index = tag.index(COLUMN_TAG.AQI)
            pm25_index = tag.index(COLUMN_TAG.PM25)
            co2_index = tag.index(COLUMN_TAG.CO2)
            index_outdoor_aqi = tag.index(COLUMN_TAG.OUTDOOR_AQI)
            index_outdoor_pm25 = tag.index(COLUMN_TAG.OUTDOOR_PM25)

            column_list = [topple[timestamp_index],
                           topple[temperature_index],
                           topple[humidity_index],
                           topple[aqi_index],
                           topple[pm25_index],
                           topple[co2_index],
                           topple[index_outdoor_aqi],
                           topple[index_outdoor_pm25]]

            column_list = [str(element) for element in column_list]
            line_to_save = ','.join(column_list)  # type: str
            current_device_data_file.write(line_to_save + '\n')
        current_device_data_file.close()


# Main code starts here:
(column_names, column_data) = read_file(path_to_file)
column_data = null_fill(column_data, column_names)
city_devices = get_cities_and_their_devices(column_data, column_names)
city_devices = save_basic_info(city_devices)
save_split_data(city_devices, column_data, column_names)
