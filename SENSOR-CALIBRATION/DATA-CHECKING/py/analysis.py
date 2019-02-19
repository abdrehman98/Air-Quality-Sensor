from collections import Counter

from matplotlib import pyplot
from matplotlib import dates
from matplotlib.collections import PatchCollection
import consts as C
import statistics
from ind import *
from load_data import Load

doc = """
All processing is applied on PM2.5
FROM PMS3003: AE values are considered
_____________________________________________________________
All the data is sampled on 10 min.
This sampling is average value of fine sampling
Fine sampling was (0.3 - 2) s based upon air quality gradient
______________________________________________________________
For all the Measurements "Air Visual" is used as ground truth
"""


def fit(x):
    x = float(x)

    # Constants
    c1 = 9.068e-07
    c2 = -0.0005488
    c3 = 0.7456
    c4 = -0.8601

    # Degrees of fit
    y3 = c1 * pow(x, 3)
    y2 = c2 * pow(x, 2)
    y1 = c3 * x
    y0 = c4

    # Final equation
    y = y3 + y2 + y1 + y0

    return y


def hist(v, r):
    y = [0] * (len(r) - 1)
    for vn in v:
        for i in range(len(y)):
            if r[i] <= vn <= r[i + 1]:
                y[i] += 1

    x = [None] * len(y)
    for i in range(len(x)):
        x[i] = "[ " + str(r[i]) + " - " + str(r[i + 1]) + " ]"

    return x, y


def main():
    F1 = C.AV_SPLIT_FOLDER_PATH + C.SPLIT_AV_FILE_NAME
    F2 = C.AV_SPLIT_FOLDER_PATH + "s1.csv"

    print(doc)

    data1 = Load.load(F1, AV)
    data2 = Load.load(F2, PMS)

    uts = data1[AV.TIMESTAMP]
    y = data1[AV.PM25]
    x = data2[PMS.AE25]

    ###
    # Computation of vectors
    ###
    # Compute: error
    x1 = [fit(k) for k in x]
    dxy = [abs(k1 - k2) for k1, k2 in zip(x1, y)]
    pxy = [round(dxy[i] / y[i], 2) * 100 for i in range(len(uts))]
    # compute: fit
    x0 = list(range(900))
    y0 = [fit(k) for k in x0]

    hx, hy = hist(pxy, list(range(0, 14, 3)))
    hy = [h / sum(hy) * 100 for h in hy]
    ###
    # GRAPH PLOTTING: starts here
    ###

    title = "PERCENTAGE-ERROR 2"
    pyplot.figure(figsize=(16 * 0.8, 7 * 0.8))
    pyplot.title(title)
    pyplot.plot(uts, pxy, "m") # , label="PERCENTAGE ERROR")
    pyplot.xlabel("Error occurred")
    pyplot.xlim([min(uts), max(uts)])
    # pyplot.ylim([0, 50])
    pyplot.grid()
    pyplot.legend()
    pyplot.gcf().autofmt_xdate()
    # pyplot.show()
    pyplot.savefig(title + ".png", dpi=200)

    """
    # Print values:
    print("Mean difference: ", statistics.mean(pxy), "%")
    print("Median of difference: ", statistics.median(pxy), "%")
    print("Mode of the difference: ", Counter(pxy).most_common(1), "%")
    print("Standard deviation: ", statistics.pstdev(pxy), "%")
    print("Variance: ", statistics.pvariance(pxy), "%")
    """

main()


"""
COLOR CONVENTION
AIR-VISUAL = BLUE
PMS3003-AE = RED
CALIBRATED-PMS3003-AE = GREEN


"""


"""
    pyplot.ylabel("Time in (Month-Day Hour)     Year = 2018")
# PLOT: data
plotter.plot(uts, av_PM25, label="AIR-VISUAL Node pro data")
plotter.plot(uts, pms_PM25, label="PMS3003 AE")
plotter.plot(uts, pms_encoded, label="MAPED PMS3003")
plotter.xlabel("TIME-STAMP")
plotter.ylabel("PM2.5 data sampled at 10 min")
plotter.legend()
plotter.grid()
plotter.show()

# Compute distances:
delavtopms = [pms_PM25[i] - av_PM25[i] for i in range(len(av_PM25))]
delavtopmsenc = [pms_encoded[i] - av_PM25[i] for i in range(len(av_PM25))]
absDelAvToPmsEnc = [abs(delavtopmsenc[i]) for i in range(len(av_PM25))]
percentAbsDelAvToPmsEnc = [(absDelAvToPmsEnc[i] / av_PM25[i]) * 100 for i in range(len(av_PM25))]

plotter.hist(percentAbsDelAvToPmsEnc)
plotter.grid()
plotter.show()

print("Mean difference: ", statistics.mean(percentAbsDelAvToPmsEnc), "%")
print("Median of difference: ", statistics.median(percentAbsDelAvToPmsEnc), "%")
print("Mode of the difference: ", Counter(percentAbsDelAvToPmsEnc).most_common(1), "%")
print("Standard deviation: ", statistics.pstdev(percentAbsDelAvToPmsEnc), "%")
print("Variance: ", statistics.pvariance(percentAbsDelAvToPmsEnc), "%")

# PLOT: Distance vectors
plotter.plot(uts, delavtopms, label="PMS - AIR VISUAL")
plotter.plot(uts, delavtopmsenc,  label="PMS CALIBRATED - AIR VISUAL")
plotter.xlabel("TIME-STAMP")
plotter.ylabel("Distance Vector")
plotter.legend()
plotter.grid()
plotter.show()
"""
"""
plotter.plot(uts, absDelAvToPmsEnc,  label="|PMS CALIBRATED - AIR VISUAL|")
plotter.plot(uts, percentAbsDelAvToPmsEnc,  label="% Difference")
plotter.xlabel("TIME-STAMP")
plotter.ylabel("Distance Vector")
plotter.legend()
plotter.grid()
plotter.show()
"""
"""
plotter.scatter(av_PM25, pms_PM25)
plotter.xlabel("AIR-VISUAL PM2.5")
plotter.ylabel("PMS3003 PM2.5 AE")
plotter.grid()
plotter.show()

plotter.scatter(av_PM25, pms_PM25, label="ACTUAL-OUTPUT")
plotter.plot(av_PM25, av_PM25, label="GROUND-TRUTH")
plotter.xlabel("AIR-VISUAL PM2.5")
plotter.ylabel("PMS3003 PM2.5 AE")
plotter.legend()
plotter.grid()
plotter.show()
"""
