import consts as C
import indices as I
import load_data
import GENERAL
from datetime import timedelta
from matplotlib import pyplot as plotter


av_split_data = load_data.air_visual_node_split(C.AV_SPLIT_FOLDER_PATH +
                                C.SPLIT_AV_FILE_NAME)
# pms_data = load_data.pms3003(C.PMS_DATA_FILE_PATH)
pms_split_data = load_data.pms3003(C.AV_SPLIT_FOLDER_PATH + "s1.csv")

uts = GENERAL.get_column(I.E_TIMESTAMP, av_split_data)
av_PM25 = GENERAL.get_column(I.E_PM25, av_split_data)
pms_PM25 = GENERAL.get_column(I.PMS_AE_2_5, pms_split_data)

"""
# PLOT: data
plotter.plot(uts,av_PM25)
plotter.plot(uts, pms_PM25)
plotter.show()

plotter.plot(uts, [pms_PM25[i] - av_PM25[i] for i in range(len(av_PM25))])
plotter.show()
"""
plotter.scatter(av_PM25, pms_PM25)
plotter.plot(av_PM25, av_PM25)
plotter.grid()
plotter.show()
# plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_data),
#              GENERAL.get_column(I.PMS_AE_2_5, pms_data))
