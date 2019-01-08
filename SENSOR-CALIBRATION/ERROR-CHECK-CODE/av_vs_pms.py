import indices as I

PMS_DATA_FILE_PATH = '../EXTRECTED-DATA/DEC-24/s1.csv'
PAQI_DATA_FILE_PATH = '../EXTRECTED-DATA/AV-RAFAY-2016-18/201812_AirVisual_values.txt'


def load_airvisual_data(file_path):
    f = open(file_path)
    line = f.readline()
    line = line.replace(line[len(line) - 1], "")
    print(line)
    line = line.split(";")
    print(len(line))
    for i in range(len(line)):
        string = line[i]  # type: str
        print(string.upper(), "=", i)


def main():
    load_airvisual_data(PAQI_DATA_FILE_PATH)


main()
