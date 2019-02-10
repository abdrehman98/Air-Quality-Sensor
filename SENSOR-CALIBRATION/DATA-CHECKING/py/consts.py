# Folder where data or Results are stored
BASE_FOLDER = "../../DATA+RESULTS/"

# Folder where input files exists
PMS_FOLDER = "DEC-24/"
PAQI_FOLDER = "AV-RAFAY-2016-18/"

# Input file paths
PMS_DATA_FILE_PATH = BASE_FOLDER + PMS_FOLDER + "s1.csv"
PAQI_DATA_FILE_PATH = BASE_FOLDER + PAQI_FOLDER + "201812_AirVisual_values.txt"


# Input files are Seperated at
PAQI_FILE_SEPARATOR = ";"
PMS_FILE_SEPARATOR = ","


AV_SPLIT_FOLDER = "SPLIT-" + PMS_FOLDER
AV_SPLIT_FOLDER_PATH = BASE_FOLDER + AV_SPLIT_FOLDER
SPLIT_AV_FILE_NAME = "AV.csv"


class PATH:
    pass
