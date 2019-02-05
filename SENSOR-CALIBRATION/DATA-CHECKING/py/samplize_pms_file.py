import consts as C
import indices as I
import load_data
import GENERAL
from datetime import timedelta
from matplotlib import pyplot as plotter

av_split_data = load_data.air_visual_node_split(C.AV_SPLIT_FOLDER_PATH +
                                C.SPLIT_AV_FILE_NAME)
pms_data = load_data.pms3003(C.PMS_DATA_FILE_PATH)

air_visual_ts = GENERAL.get_column(I.E_TIMESTAMP, av_split_data)

plotter.plot(GENERAL.get_column(I.E_TIMESTAMP, av_split_data),
             GENERAL.get_column(I.E_PM25, av_split_data))
plotter.plot(GENERAL.get_column(I.PMS_TIMESTAMP, pms_data),
             GENERAL.get_column(I.PMS_AE_2_5, pms_data))
plotter.show()
pms_sample_data = []
for i in range(len(air_visual_ts)):
    lower = air_visual_ts[i] - timedelta(minutes=5)
    upper = air_visual_ts[i] + timedelta(minutes=5)
    data_grid = GENERAL.get_data_between(pms_data,
                                         lower,
                                         upper,
                                         index=I.PMS_TIMESTAMP)
    print(len(data_grid))
    vec = []
    if len(data_grid) == 0:
        vec = [-1, -1, -1, -1, -1, -1]
    else:
        for j in range(6):
            val = GENERAL.get_column(j, data_grid)
            val = sum(val) / len(val)
            vec.append(int(val))
    vec.append(air_visual_ts[i])
    print(vec)
    pms_sample_data.append(vec)


print("saving data")
f = open(C.AV_SPLIT_FOLDER_PATH + "s1.csv", "w+")
for line in pms_sample_data:
    line[6] = line[6].timestamp()
    line = [str(ele) for ele in line]
    line = ','.join(line)
    f.write(line + "\n")
