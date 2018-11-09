import data_gap
import GENERAL
from datetime import datetime, timedelta

v = GENERAL.datetime_range(datetime(year=2013, month=11, day=5),
                           datetime(year=2013, month=11, day=4),
                           timedelta(hours=-3))
print data_gap.compute_gap_vector(v)
