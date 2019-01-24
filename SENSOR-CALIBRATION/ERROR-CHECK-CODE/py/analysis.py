import consts as C
import indices as I
import load_data
import GENERAL
from datetime import timedelta
from matplotlib import pyplot as plotter


av_split_data = load_data.air_visual_node_split(C.AV_SPLIT_FOLDER_PATH +
                                C.SPLIT_AV_FILE_NAME)
pms_data = load_data.pms3003(C.PMS_DATA_FILE_PATH)
pms_split_data = load_data.pms3003(C.AV_SPLIT_FOLDER_PATH + "s1.csv")

plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, av_split_data),
             GENERAL.get_column(I.E_PM25, av_split_data))
plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_data),
             GENERAL.get_column(I.PMS_AE_2_5, pms_data))
plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_split_data),
             GENERAL.get_column(I.PMS_AE_2_5, pms_split_data))
plotter.show()