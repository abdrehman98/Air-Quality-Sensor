import GENERAL
import consts as C
import indices as I
import load_data
from matplotlib import pyplot as plotter

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


print(doc)
av_split_data = load_data.air_visual_node_split(C.AV_SPLIT_FOLDER_PATH +
                                C.SPLIT_AV_FILE_NAME)
# pms_data = load_data.pms3003(C.PMS_DATA_FILE_PATH)
pms_split_data = load_data.pms3003(C.AV_SPLIT_FOLDER_PATH + "s1.csv")

uts = GENERAL.get_column(I.E_TIMESTAMP, av_split_data)
av_PM25 = GENERAL.get_column(I.E_PM25, av_split_data)
pms_PM25 = GENERAL.get_column(I.PMS_AE_2_5, pms_split_data)
pms_encoded = [fit(pms_PM25[i]) for i in range(len(pms_PM25))]

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

"""
# PLOT: Distance vectors
plotter.plot(uts, delavtopms, label="PMS - AIR VISUAL")
plotter.plot(uts, delavtopmsenc,  label="PMS CALIBRATED - AIR VISUAL")
plotter.xlabel("TIME-STAMP")
plotter.ylabel("Distance Vector")
plotter.legend()
plotter.grid()
plotter.show()
"""

plotter.plot(uts, absDelAvToPmsEnc,  label="|PMS CALIBRATED - AIR VISUAL|")
plotter.plot(uts, percentAbsDelAvToPmsEnc,  label="% Difference")
plotter.xlabel("TIME-STAMP")
plotter.ylabel("Distance Vector")
plotter.legend()
plotter.grid()
plotter.show()

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
