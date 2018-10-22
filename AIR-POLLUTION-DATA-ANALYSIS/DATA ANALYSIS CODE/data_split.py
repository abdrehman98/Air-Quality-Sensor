import tags as column_tag
import os

BASIC_PATH = '../DATA/PAQI 11-10-2018'
path_to_file = BASIC_PATH + '.csv'
PATH_TO_BASIC_RES = BASIC_PATH + '/BASIC-RESULT.txt'
SEPERATOR = '<>@<>'


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
    print 'Total number of lines read from file: ', len(file_content)

    split_lines = []
    for line in file_content:
        split_lines.append(line.replace('\n', '').split(','))
    print 'Number of columns in File', len(split_lines[0])
    return split_lines[0], split_lines[1:len(split_lines)]


def null_fill(column, tag):
    for n in range(0, len(column)):
        if '' == column[n][tag.index(column_tag.OUTDOOR_PM25)]:
            column[n][tag.index(column_tag.OUTDOOR_PM25)] = -1

        if '' == column[n][tag.index(column_tag.OUTDOOR_AQI)]:
            column[n][tag.index(column_tag.OUTDOOR_AQI)] = -1

        if '' == column[n][tag.index(column_tag.KEY)]:
            column[n][tag.index(column_tag.KEY)] = 'null'

        if '' == column[n][tag.index(column_tag.CITY)]:
            print 'Critical error city name null at line:', n +1

        if '' == column[n][tag.index(column_tag.AREA)]:
            print 'Critical error area null at line:', n + 1

    return column


def get_cities_and_their_devices(data, tag):
    city_index = tag.index(column_tag.CITY)
    unique_cities = set([row[city_index] for row in data])
    list_of_cities_and_devices = []
    for selected_city in unique_cities:
        devices_list = []

        for topple in data:
            if topple[tag.index(column_tag.CITY)] == selected_city:
                devices_list.append(topple[tag.index(column_tag.AREA)])

        list_of_cities_and_devices.append((selected_city, set(devices_list)))

    return list_of_cities_and_devices


def save_basic_info(data):
    try:
        os.makedirs(BASIC_PATH)
        print 'Creating folder ', BASIC_PATH
    except:
        print 'Folder already exists', BASIC_PATH

    basic_res_file = open(PATH_TO_BASIC_RES, "w+")
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

    basic_res_file = open(PATH_TO_BASIC_RES, "a+")
    basic_res_file.write(SEPERATOR + '\n')

    for city_device_topple in list_city_device:
        city = city_device_topple[0]
        device = city_device_topple[1]

        current_device_data = []
        for data_topple in data:  # type: list
            if city == data_topple[tag.index(column_tag.CITY)] and \
                    device == data_topple[tag.index(column_tag.AREA)]:
                current_device_data.append(data_topple)

        current_device_key = set(get_column(tag.index(column_tag.KEY), current_device_data))
        current_device_lat = set(get_column(tag.index(column_tag.LATITUDE), current_device_data))
        current_device_lon = set(get_column(tag.index(column_tag.LONGITUDE), current_device_data))
        current_device_install_time = set(get_column(tag.index(column_tag.SETUP_TIME), current_device_data))

        file_name = city + ' | ' + device
        string_to_write = file_name + SEPERATOR + str(current_device_key) + SEPERATOR
        string_to_write = string_to_write + '(' + str(current_device_lat) + ',' + str(current_device_lon) + ')'
        string_to_write = string_to_write + SEPERATOR
        string_to_write = string_to_write + str(current_device_install_time) + SEPERATOR
        string_to_write = string_to_write + str(len(current_device_data))
        basic_res_file.write(string_to_write + '\n')

        current_device_data_file = open(BASIC_PATH + '/' + file_name + '.csv', "w+")
        for topple in current_device_data:
            timestamp_index = tag.index(column_tag.TIME_STAMP)
            temperature_index = tag.index(column_tag.TEMPERATURE)
            humidity_index = tag.index(column_tag.HUMIDITY)
            aqi_index = tag.index(column_tag.AQI)
            pm25_index = tag.index(column_tag.PM25)
            co2_index = tag.index(column_tag.CO2)
            index_outdoor_aqi = tag.index(column_tag.OUTDOOR_AQI)
            index_outdoor_pm25 = tag.index(column_tag.OUTDOOR_PM25)
            column_list = []
            column_list.append(topple[timestamp_index])
            column_list.append(topple[temperature_index])
            column_list.append(topple[humidity_index])
            column_list.append(topple[aqi_index])
            column_list.append(topple[pm25_index])
            column_list.append(topple[co2_index])
            column_list.append(topple[index_outdoor_aqi])
            column_list.append(topple[index_outdoor_pm25])
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
