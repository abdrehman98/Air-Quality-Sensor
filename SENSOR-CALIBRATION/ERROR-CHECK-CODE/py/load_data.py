import indices as I
from datetime import datetime, timedelta
import consts as C


def air_visual_node(file_path):

    # open file for reading data
    data = open(file_path).readlines()
    # Remove Tags
    data.remove(data[0])
    print("Loading Air visuals data . . .")
    structured_data = []

    for line in data:
        # Remove Separator from the end of line
        end_line_char = line[len(line) - 1]
        line = line.replace(end_line_char, "")

        # Split entries on token
        line = line.split(C.PAQI_FILE_SEPARATOR)

        if len(line) == 14:
            eline = [None] * I.NUMBER_OF_ENCODED_COLUMNS

            eline[I.E_TIMESTAMP] = datetime.utcfromtimestamp(float(line[I.TIMESTAMP])) -\
                                   timedelta(hours=5)
            eline[I.E_AQI] = float(line[I.AQI_US])
            eline[I.E_PM25] = float(line[I.PM2_5])
            eline[I.E_PM10] = float(line[I.PM10])
            eline[I.E_TEMPERATURE] = float(line[I.TEMPERATURE_C])
            eline[I.E_HUMIDITY] = float(line[I.HUMIDITY_RH])
            structured_data.append(eline)

    print("Done loading!!")
    return structured_data


def pms3003(file_path):

    # open file for reading data
    data = open(file_path).readlines()
    print("Loading PMS3003 data . . .")
    structured_data = []

    for line in data:
        # Remove Separator from the end of line
        line = line.replace(line[len(line) - 1], "")
        # Split entries on token
        line = line.split(C.PMS_FILE_SEPARATOR)

        if len(line) == I.PMS_NUMBER_DATA_COLUMNS:
            for i in range(len(line)):
                line[i] = float(line[i])
            line[I.PMS_TIMESTAMP] = datetime.utcfromtimestamp(line[I.PMS_TIMESTAMP])
            structured_data.append(line)

    print("Done loading !!")
    return structured_data


def air_visual_node_split(file_path):

    # open file for reading data
    data = open(file_path).readlines()

    print("Loading Air visual split data data . . .")
    structured_data = []

    for line in data:
        line = line.replace("\n", "")
        line = line.split(",")

        if len(line) == :
            # convert all column into float
            line = [float(val) for val in line]
            line[I.E_TIMESTAMP] = datetime.utcfromtimestamp(line[I.E_TIMESTAMP])
            structured_data.append(line)

    print("Done loading!!")
    return structured_data


