TIME_STAMP_INDEX = 7;
GUASS_FILTER_WINDOW_SIZE = 10;
%guass_filter = gausswin(GUASS_FILTER_WINDOW_SIZE) ./ GUASS_FILTER_WINDOW_SIZE;
guass_filter  = (1/GUASS_FILTER_WINDOW_SIZE) * ones(1,GUASS_FILTER_WINDOW_SIZE);

data_s1 = csvread('../EXTRECTED-DATA/DEC-2/s1.csv');
data_s2 = csvread('../EXTRECTED-DATA/DEC-2/s2.csv');
data_s3 = csvread('../EXTRECTED-DATA/DEC-2/s3.csv');

ts1 = data_s1(:, TIME_STAMP_INDEX);
ts2 = data_s2(:, TIME_STAMP_INDEX);
ts3 = data_s3(:, TIME_STAMP_INDEX);

pm25_sp_1 = data_s1(:, 2);
pm25_sp_2 = data_s2(:, 2);
pm25_sp_3 = data_s3(:, 2);

% pm25_sp_1 = filter(guass_filter, 1, pm25_sp_1);
% pm25_sp_2 = filter(guass_filter, 1, pm25_sp_2);
% pm25_sp_3 = filter(guass_filter, 1, pm25_sp_3);

ts1 = datetime(ts1, 'ConvertFrom', 'posixtime');
ts2 = datetime(ts2, 'ConvertFrom', 'posixtime');
ts3 = datetime(ts3, 'ConvertFrom', 'posixtime');

start_ts = max([min(ts1) min(ts2) min(ts3)]);
end_ts = min([max(ts1) max(ts2) max(ts3)]);
uts = start_ts:minutes(15):end_ts;

to_pm25_sp_1 = zeros(size(uts));
to_pm25_sp_2 = zeros(size(uts));
to_pm25_sp_3 = zeros(size(uts));

for index = 1:length(uts) - 1
    lower = uts(index);
    upper = uts(index + 1);

    logic_indicies = ts1 >= lower & ts1 < upper;
    to_pm25_sp_1(index) = mean(pm25_sp_1(logic_indicies));

    logic_indicies = ts2 >= lower & ts2 < upper;
    to_pm25_sp_2(index) = mean(pm25_sp_2(logic_indicies));

    logic_indicies = ts3 >= lower & ts3 < upper;
    to_pm25_sp_3(index) = mean(pm25_sp_3(logic_indicies));
end

% plot(ts1, pm25_sp_1, 'r', ts2, pm25_sp_2, 'b', ts3, pm25_sp_3, 'g')
% pause(5)
plot(uts, to_pm25_sp_1, 'r' , uts, to_pm25_sp_2, 'b', uts, to_pm25_sp_3, 'g');
% plot(uts, abs(to_pm25_sp_1 - to_pm25_sp_2), 'r', uts, abs(to_pm25_sp_2 - to_pm25_sp_3), 'b', uts, abs(to_pm25_sp_1 - to_pm25_sp_3), 'g');

clear