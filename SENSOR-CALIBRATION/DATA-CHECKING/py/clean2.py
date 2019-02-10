from matplotlib import pyplot
from tqdm import tqdm
from GENERAL import progress
import GENERAL
from load_data import *

MISSING_VALUE = -1
F1 = C.AV_SPLIT_FOLDER_PATH + C.SPLIT_AV_FILE_NAME
F2 = C.PMS_DATA_FILE_PATH

BF = C.AV_SPLIT_FOLDER_PATH
RF = "s1.csv"


def sample(uts, data, obj, td):
    print("Sampling begin")
    res = []
    nc = obj.LENGTH
    for col in range(nc):
        res.append([])

    lts = len(uts)
    for n in tqdm(range(lts)):
        ts = uts[n]
        n0, n1 = (ts-td, ts+td)
        grid = GENERAL.get_data_between(data, n0, n1,
                                        obj.TIMESTAMP)
        sl = len(grid[0])
        for col in range(nc):

            if col == obj.TIMESTAMP:
                point = ts
            elif sl == 0:
                point = MISSING_VALUE
            else:
                point = round(sum(grid[col]) / sl)

            res[col].append(point)
    print()
    return res


def main():
    standard = Load.load(F1, AV)
    precise = Load.load(F2, PMS)

    uts = standard[AV.TIMESTAMP]
    td = timedelta(minutes=5)
    sampled = sample(uts, precise, PMS, td)

    pyplot.plot(standard[AV.TIMESTAMP], standard[AV.PM25], label="Standard Sampling")
    pyplot.plot(sampled[PMS.TIMESTAMP], sampled[PMS.AE25], label="Sampled data")
    pyplot.plot(precise[PMS.TIMESTAMP], precise[PMS.AE25], label="Precised data")
    pyplot.legend()
    pyplot.show()
    Save.save(BF, RF, sampled, PMS)


main()
