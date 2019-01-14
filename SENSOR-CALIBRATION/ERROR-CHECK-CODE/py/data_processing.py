import indices as I
from datetime import datetime, timedelta
import consts as C
import os

from matplotlib import pyplot as plotter
import GENERAL


def load_air_visual_data(file_path):

    # open file for reading data
    data = open(file_path).readlines()
    # Remove Tags
    data.remove(data[0])
    print("Loading Air visuals data . . .")
    structured_data = []

    for line in data:
        # Remove Separator from the end of line
        line = line.replace(line[len(line) - 1], "")
        # Split entries on token
        line = line.split(C.PAQI_FILE_SEPARATOR)

        if len(line) == 14:
            eline = [None] * I.NUMBER_OF_ENCODED_COLUMNS

            eline[I.E_TIMESTAMP] = datetime.fromtimestamp(
                float(line[I.TIMESTAMP])) - timedelta(hours=10)
            eline[I.E_AQI] = float(line[I.AQI_US])
            eline[I.E_PM25] = float(line[I.PM2_5])
            eline[I.E_PM10] = float(line[I.PM10])
            eline[I.E_TEMPERATURE] = float(line[I.TEMPERATURE_C])
            eline[I.E_HUMIDITY] = float(line[I.HUMIDITY_RH])
            structured_data.append(eline)

    print("Done loading!!")
    return structured_data


def load_pms_data(file_path):

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


def save_air_visual_data(folder_path, file_path, data):


def main():
    paqi_data = load_air_visual_data(C.PAQI_DATA_FILE_PATH)
    pms_data = load_pms_data(C.PMS_DATA_FILE_PATH)
    ts = [line[I.PMS_TIMESTAMP] for line in pms_data]
    min_pms_ts = min(ts)
    max_pms_ts = max(ts)
    print("MIN=", min_pms_ts)
    print("MAX=", max_pms_ts)

    paqi_considered_data = GENERAL.get_data_between(paqi_data, min_pms_ts, max_pms_ts, I.E_TIMESTAMP)

    """
    folder_path = C.BASE_PATH + C.SPLIT + C.PMS_FOLDER_PATH
    file_path = folder_path + "AV.csv"
    print(folder_path)
    print(file_path)
    os.makedirs(folder_path, exist_ok=True)
    f = open(file_path, "w")
    for line in paqi_considered_data:
        line[I.E_TIMESTAMP] = str(line[I.E_TIMESTAMP].timestamp())
        line[I.E_AQI] = str(line[I.E_AQI])
        line[I.E_PM25] = str(line[I.E_PM25])
        line[I.E_PM10] = str(line[I.E_PM10])
        line[I.E_TEMPERATURE] = str(line[I.E_TEMPERATURE])
        line[I.E_HUMIDITY] = str(line[I.E_HUMIDITY])
        line = ",".join(line)
        f.write(line + "\n")
    f.close()
    """


main()
