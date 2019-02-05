import indices as I
from datetime import datetime, timedelta
import consts as C
from ind import RAV


class Load:
    @staticmethod
    def _grid(l):
        g = list()
        for i in range(l):
            g.append([])
        return g

    @staticmethod
    def load(path, obj):

        # Read all data from file
        # It is in row major format
        data = open(path).readlines()

        # create column major grid
        res = Load._grid(obj.LENGTH)

        for row in data:

            # Remove end-line character and
            # split data on the basis of sep
            el = row[len(row) - 1]
            row = row.replace(el, "")
            row = row.split(",")

            # Encode every value of line and append
            for i in range(len(row)):
                val = eval(row[i])
                if i == obj.TIMESTAMP:
                    val = datetime.fromtimestamp(val)
                res[i].append(val)

        return res

    @staticmethod
    def av(path):
        data = open(path).readlines()

        # Remove First line
        # It is columns Tags(names)
        data.remove(data[0])

        # Create Column major : Grid
        res = Load._grid(AV.LENGTH)

        for row in data:
            # Remove end-line character
            # split data on the basis of sep
            el = row[len(row) - 1]
            row = row.replace(el, "")
            row = row.split(",")

            # Skip the corrupt value
            if len(row) != RAV.LENGTH:
                continue

            # ENCODE: TIMESTAMP
            fts = eval(row[RAV.TIMESTAMP])
            dts = datetime.fromtimestamp(fts)
            res[AV.TIMESTAMP].append(dts)

            # ENCODE: DATA
            res[AV.AQI].append(eval(row[RAV.AQI_US]))
            res[AV.PM25].append(eval(row[RAV.PM2_5]))
            res[AV.PM10].append(eval(row[RAV.PM10]))
            res[AV.TEMPERATURE].append(
                eval(row[RAV.TEMPERATURE_C]))
            res[AV.HUMIDITY].append(
                eval(row[RAV.HUMIDITY_RH]))

        return res


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
            fts = float(line[I.TIMESTAMP]) - 18000

            ts = datetime.fromtimestamp(fts)
            eline[I.E_TIMESTAMP] = ts
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
            fts = line[I.PMS_TIMESTAMP]
            ts = datetime.fromtimestamp(fts)
            line[I.PMS_TIMESTAMP] = ts
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

        if len(line) == 6:
            # convert all column into float
            line = [float(val) for val in line]
            ts = line[I.E_TIMESTAMP]
            ts = datetime.fromtimestamp(ts)
            line[I.E_TIMESTAMP] = ts
            structured_data.append(line)

    print("Done loading!!")
    return structured_data


