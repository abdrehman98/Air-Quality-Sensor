import indices as I
from datetime import datetime, timedelta
import consts as C
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


def main():
    paqi_data = load_air_visual_data(C.PAQI_DATA_FILE_PATH)
    pms_data = load_pms_data(C.PMS_DATA_FILE_PATH)
    plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, paqi_data),
                 GENERAL.get_column(I.E_PM25, paqi_data),
                 label="Air Visual: PM2.5")

    plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_data),
                 GENERAL.get_column(I.PMS_AE_2_5, pms_data),
                 label="PMS3003: PM2.5 AE format")
    plotter.grid()
    plotter.xlabel("Timestamp")
    plotter.ylabel("PM2.5 concentration: ug/m^3")
    plotter.title("PMS3003 VS Air visual (pro node)")
    plotter.legend()
    plotter.show()


main()
