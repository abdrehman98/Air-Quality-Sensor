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
    print '------>', 'Hello! ', PROGRAM.PROGRAM_NAME, ' is here to help you.'
    print '----->', 'Be patient ', PROGRAM.PROGRAM_NAME, 'is loading files for you.'

    # Load files
    devices_names_list, devices_data_list = loader.load_data(PROGRAM.DATA_FOLDER_PATH)
    print PROGRAM.PROGRAM_NAME, ' brought your files, Oooops they are very heavy.'

    data_gap.print_all_devices_time_info(devices_names_list, devices_data_list)
    # data_gap.plot_data_gap(devices_names_list, devices_data_list)
    # data_gap.compute_plot_gap(devices_names_list, devices_data_list)
    ##
    # Testing dance here
    # lets dance

    ##
    # for device_1 in devices_data_list:
    #     device_1_time = PROGRAM.get_column(INDEX.TIMESTAMP, device_1)
    #     device_1_time = data_gap.compute_gap_vector(device_1_time)
    #     # plotter.plot(device_1_time)
    #     # plotter.show()
    #     device_1_time = data_gap.compute_gap_distribution(device_1_time)
    #     plotter.plot(device_1_time[0], device_1_time[1])
    #     plotter.show()


main()
