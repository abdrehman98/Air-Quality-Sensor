import indices as I
import os


def air_visual_node_split(data, file_name, folder_path=""):

    file_path = folder_path + file_name
    if folder_path != "":
        print("Creating Directory", folder_path)
        os.makedirs(folder_path, exist_ok=True)

    print("Saving file to", file_path)
    f = open(file_path, "w")

    for line in data:
        line[I.E_TIMESTAMP] = str(line[I.E_TIMESTAMP].timestamp())
        line[I.E_AQI] = str(line[I.E_AQI])
        line[I.E_PM25] = str(line[I.E_PM25])
        line[I.E_PM10] = str(line[I.E_PM10])
        line[I.E_TEMPERATURE] = str(line[I.E_TEMPERATURE])
        line[I.E_HUMIDITY] = str(line[I.E_HUMIDITY])
        line = ",".join(line)
        f.write(line + "\n")
    f.close()