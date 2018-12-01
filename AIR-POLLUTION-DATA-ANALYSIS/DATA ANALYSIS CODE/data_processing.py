import matplotlib.pyplot as plotter
import data_gap
import data_loader_for_processing as loader

import GENERAL as PROGRAM
import PROCESSED_DATA_INDEX as INDEX


##
# Making main the entry
# to avoid making global variables


def main():
    # Hello Remarks!
    print('------>', 'Hello! ', PROGRAM.PROGRAM_NAME, ' is here to help you.')
    print('----->', 'Be patient ', PROGRAM.PROGRAM_NAME, 'is loading files for you.')

    # Load files
    devices_names_list, devices_data_list = loader.load_data(PROGRAM.DATA_FOLDER_PATH)
    print(PROGRAM.PROGRAM_NAME, ' brought your files, Oooops they are very heavy.')

    data_gap.compute_plot_sparsity(devices_names_list, devices_data_list)
    data_gap.compute_plot_gap(devices_names_list, devices_data_list)


main()
