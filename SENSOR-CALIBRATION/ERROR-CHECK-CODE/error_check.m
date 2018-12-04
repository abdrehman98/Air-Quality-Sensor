TIME_STAMP_INDEX = 7;
data_s1 = csvread('../EXTRECTED-DATA/DEC-2/s1.csv');
data_s2 = csvread('../EXTRECTED-DATA/DEC-2/s2.csv');
data_s3 = csvread('../EXTRECTED-DATA/DEC-2/s3.csv');

ts1 = datetime(data_s1(:, TIME_STAMP_INDEX), 'ConvertFrom', 'posixtime');
ts2 = datetime(data_s2(:, TIME_STAMP_INDEX), 'ConvertFrom', 'posixtime');
ts3 = datetime(data_s3(:, TIME_STAMP_INDEX), 'ConvertFrom', 'posixtime');

pm25_sp_1 = data_s1(:, 2);
pm25_sp_2 = data_s2(:, 2);
pm25_sp_3 = data_s3(:, 2);

plot(ts1, pm25_sp_1, ts2, pm25_sp_2, ts3, pm25_sp_3)