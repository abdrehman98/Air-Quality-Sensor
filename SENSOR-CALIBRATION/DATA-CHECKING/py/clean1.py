import GENERAL
import consts as C
import indices as I
import load_data
import os
import save_data
from load_data import *
from ind import *
from datetime import datetime, timedelta
from matplotlib import pyplot as plotter


def main():
    F1 = C.PAQI_DATA_FILE_PATH
    F2 = C.PMS_DATA_FILE_PATH

    SB = C.AV_SPLIT_FOLDER_PATH
    SF = C.SPLIT_AV_FILE_NAME

    large = Load.load(F1, RAV)
    part = Load.load(F2, PMS)

    ts = part[PMS.TIMESTAMP]
    mints = min(ts)
    maxts = max(ts)
    print("MIN=", mints)
    print("MAX=", maxts)

    extract = GENERAL.get_data_between(large, mints,
                                       maxts, AV.TIMESTAMP)
    Save.save(SB, SF, extract, AV)

    plotter.plot(large[AV.TIMESTAMP], large[AV.PM25])
    plotter.plot(part[PMS.TIMESTAMP], part[PMS.AE25])
    plotter.plot(extract[AV.TIMESTAMP], extract[AV.PM25])
    plotter.show()


main()
