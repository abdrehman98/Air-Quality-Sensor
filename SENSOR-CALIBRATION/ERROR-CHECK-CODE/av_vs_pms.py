import indices as I
from datetime import datetime
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

            eline[I.E_TIMESTAMP] = datetime.fromtimestamp(float(line[I.TIMESTAMP]))
            eline[I.E_AQI] = float(line[I.AQI_US])
            eline[I.E_PM25] = float(line[I.PM2_5])
            eline[I.E_PM10] = float(line[I.PM10])
            eline[I.E_TEMPERATURE] = float(line[I.TEMPERATURE_C])
            eline[I.E_HUMIDITY] = float(line[I.HUMIDITY_RH])
            structured_data.append(eline)

    print("Done loading . . .")
    return structured_data


def main():
    paqi_data = load_air_visual_data(C.PAQI_DATA_FILE_PATH)
    # pms_data = load_data(C.PMS_DATA_FILE_PATH, C.PMS_FILE_SEPARATOR, 7)
    plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, paqi_data),
                 GENERAL.get_column(I.E_PM25, paqi_data))
    plotter.show()


main()
