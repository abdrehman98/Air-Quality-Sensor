import indices as I
import consts as C


def generate_cloumns(file_path):
    f = open(file_path)
    line = f.readline()
    line = line.replace(line[len(line) - 1], "")
    print(line)
    line = line.split(C.PAQI_FILE_SEPARATOR)
    print(len(line))
    for i in range(len(line)):
        string = line[i]  # type: str
        print(string.upper(), "=", i)


generate_cloumns(C.PAQI_DATA_FILE_PATH)
