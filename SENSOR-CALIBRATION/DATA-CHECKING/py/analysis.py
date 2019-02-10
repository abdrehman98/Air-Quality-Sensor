from matplotlib import pyplot as pyplot

import consts as C
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
TITLE = "PM2.5: PMS3003 and Air visual"


def fit(x):
    x = float(x)

    # Constants
    p1 = 9.068e-07
    p2 = -0.0005488
    p3 = 0.7456
    p4 = -0.8601

    # Degrees of fit
    y3 = p1 * (x ** 3)
    y2 = p2 * (x ** 2)
    y1 = p3 * x
    y0 = p4

    # Final equation
    y = y3 + y2 + y1 + y0

    return y


def main():
    F1 = C.AV_SPLIT_FOLDER_PATH + C.SPLIT_AV_FILE_NAME
    F2 = C.AV_SPLIT_FOLDER_PATH + "s1.csv"

    print(doc)

    data1 = Load.load(F1, AV)
    data2 = Load.load(F2, PMS)

    uts = data1[AV.TIMESTAMP]
    y = data1[AV.PM25]
    x = data2[PMS.AE25]

    x1 = [fit(k) for k in x]

    ###############################################
    ###############################################
    ########### Custom plotting starts here #######
    ###############################################
    ###############################################

    pyplot.title(TITLE)
    pyplot.plot(uts, x)
    pyplot.plot(uts, x1)
    pyplot.plot(uts, y)
    pyplot.show()


main()


"""
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
