from datetime import timedelta

import matplotlib.pyplot as plotter

import GENERAL as PROGRAM
import PROCESSED_DATA_INDEX as INDEX


##################################################################################
# Visualization of data gaps by plotting them                                    #
# 1) Computer min/max time vectors                                               #
# 1.1) Compute the time when first device was deployed and last device sent data #
# 1.2) Generate Universal TIMESTAMP vector.                                      #
# 1.3) Compute Sparsity Grid                                                     #
# 1.3.1) Compute Sparsity vector for every device                                #
# 1.3.2) Append them in the same order                                           #
# 1.4) Plot sparsity                                                             #
##################################################################################


################################
##
# Computing sparsity vector refer to Algorithm 1:
def compute_sparsity_vector(uts_vector, device_time, h):
    len_time = len(device_time)
    sparsity_vector = []
    n = 0

    for i in uts_vector:
        if n >= len_time:
            sparsity_vector.append(0)
        elif device_time[n] != i:
            sparsity_vector.append(0)
        else:
            n = n + 1
            sparsity_vector.append(h)

    return sparsity_vector


def compute_sparsity_grid(uts_vector, devices_data_list):
    # computing sparsity grid
    sparsity_grid = []
    number_of_devices = len(devices_data_list)

    # Compute sparsity vector for each device
    for k in range(0, number_of_devices):
        chosen_device = devices_data_list[k]
        device_time = PROGRAM.get_column(INDEX.TIMESTAMP, chosen_device)
        # compute the step size to indicate data availability
        h = k + 1
        # compute and append vectors
        sparsity_vector = compute_sparsity_vector(uts_vector, device_time, h)
        sparsity_grid.append(sparsity_vector)

    return sparsity_grid


def compute_gap_vector(time_vector):
    delta = []

    for i in range(0, len(time_vector) - 1):
        del_time = time_vector[i] - time_vector[i + 1]  # type:timedelta
        del_time = del_time.total_seconds() / (60 * 60) - 1
        del_time = int(del_time)
        delta.append(del_time)

    return delta


def compute_gap_distribution(gap_vector):
    max_gap = max(gap_vector)
    gap_value = range(1, max_gap + 1)
    gap_distribution = []

    for i in gap_value:
        gap_distribution.append(gap_vector.count(i))

    return gap_value, gap_distribution


def plot_sparsity_grid(devices_names, sparsity_grid, uts_vector):
    i = 0
    for sparsity_vector in sparsity_grid:
        plotter.plot(uts_vector, sparsity_vector)
        plotter.title(devices_names[i])
        plotter.show()
        i = i + 1


def plot_data_gap(devices_names_list, devices_data_list):

    # Lists to store min/max of devices
    min_time = PROGRAM.find_min_vector(devices_data_list)
    max_time = PROGRAM.find_max_vector(devices_data_list)

    # Find when First device was deployed and last data was updated
    first_device_deploy_time = min(min_time)
    last_data_update = max(max_time)

    # computing universal time vector
    uts_vector = PROGRAM.datetime_range(last_data_update,
                                        first_device_deploy_time,
                                        timedelta(hours=-1))

    # compute sparsity grid according to universal timestamp vector
    sparsity_grid = compute_sparsity_grid(uts_vector, devices_data_list)

    # Plotting sparsity grid:
    plot_sparsity_grid(devices_names_list, sparsity_grid, uts_vector)


###############################################################################
# Printing data gapes                                                         #
# 1) when device was set up and what was the last received value              #
# 2) Ideal number of data points in each device                               #
# 3) Number of data points received in                                        #
# 4) Number of data points lost                                               #
# 5) Percentage of data lost                                                  #
###############################################################################

def print_device_time_info(time_column):

    # Find minimum and maximum date
    min_time = min(time_column)
    max_time = max(time_column)

    # print device starting and Ending time
    print 'Device starting time: ', min_time
    print 'Last retrieved Data:  ', max_time

    # Find number of hours passed after starting device
    time_d = max_time - min_time   # type: timedelta
    ideal_points = time_d.total_seconds() / (60 * 60)
    print 'Estimated pints:              ', ideal_points

    # Find number of available samples and print
    available_points = len(time_column)
    print 'Total containing data points: ', available_points

    # Find data lose total and percentage
    data_lose = ideal_points - available_points
    percentage_data_loss = (1 - available_points / ideal_points) * 100.0
    print 'Total data lose:              ', data_lose, '(Data points)'
    print 'Percent data lose:              ', percentage_data_loss, '%'


def print_all_devices_time_info(device_name, device_data):
    for n in range(len(device_name)):
        print '----------------------------------------------'
        print device_name[n]
        print_device_time_info(PROGRAM.get_column(INDEX.TIMESTAMP, device_data[n]))
