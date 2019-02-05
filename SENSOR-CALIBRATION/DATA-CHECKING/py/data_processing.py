import GENERAL
import consts as C
import indices as I
import load_data
import os
import save_data
from datetime import datetime, timedelta
from matplotlib import pyplot as plotter


def main():
    paqi_data = load_data.air_visual_node(C.PAQI_DATA_FILE_PATH)
    pms_data = load_data.pms3003(C.PMS_DATA_FILE_PATH)

    ts = [line[I.PMS_TIMESTAMP] for line in pms_data]
    min_pms_ts = min(ts)
    max_pms_ts = max(ts)
    print("MIN=", min_pms_ts)
    print("MAX=", max_pms_ts)

    paqi_considered_data = GENERAL.get_data_between(paqi_data, min_pms_ts, max_pms_ts, I.E_TIMESTAMP)
    save_data.air_visual_node_split(paqi_considered_data,
                                    C.SPLIT_AV_FILE_NAME,
                                    C.AV_SPLIT_FOLDER_PATH)

    plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_data),
                 GENERAL.get_column(I.PMS_AE_2_5, pms_data))
    plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, paqi_data),
                 GENERAL.get_column(I.E_PM25, paqi_data))
    plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, paqi_considered_data),
                 GENERAL.get_column(I.E_PM25, paqi_considered_data))
    plotter.show()


main()
