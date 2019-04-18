import indices as I
import os


def air_visual_node_split(data, file_name, folder_path=""):

    file_path = folder_path + file_name
    if folder_path != "":
        print("Creating Directory", folder_path)
        os.makedirs(folder_path, exist_ok=True)

    print("Saving file to", file_path)
    f = open(file_path, "w+")

    for line in data:
        eline = [None] * len(line)
        eline[I.E_TIMESTAMP] = str(line[I.E_TIMESTAMP].timestamp())
        eline[I.E_AQI] = str(line[I.E_AQI])
        eline[I.E_PM25] = str(line[I.E_PM25])
        eline[I.E_PM10] = str(line[I.E_PM10])
        eline[I.E_TEMPERATURE] = str(line[I.E_TEMPERATURE])
        eline[I.E_HUMIDITY] = str(line[I.E_HUMIDITY])
        eline = ",".join(eline)
        f.write(eline + "\n")

    f.close()
