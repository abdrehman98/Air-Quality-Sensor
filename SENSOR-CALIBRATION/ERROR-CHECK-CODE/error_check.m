TIME_STAMP_INDEX = 7;
GUASS_FILTER_WINDOW_SIZE = 10;
MIN_STEP = 1
MAX_STEP = 60
%guass_filter = gausswin(GUASS_FILTER_WINDOW_SIZE) ./ GUASS_FILTER_WINDOW_SIZE;
guass_filter  = (1/GUASS_FILTER_WINDOW_SIZE) * ones(1,GUASS_FILTER_WINDOW_SIZE);

data_s1 = csvread('../EXTRECTED-DATA/DEC-2/s1.csv');
data_s2 = csvread('../EXTRECTED-DATA/DEC-2/s2.csv');
data_s3 = csvread('../EXTRECTED-DATA/DEC-2/s3.csv');
disp('Data loaded')

% --------------------------------------------------------
% ----------------- Extract and Encode Timestamp ---------
ts1 = data_s1(:, TIME_STAMP_INDEX);
ts2 = data_s2(:, TIME_STAMP_INDEX);
ts3 = data_s3(:, TIME_STAMP_INDEX);
ts1 = datetime(ts1, 'ConvertFrom', 'posixtime');
ts2 = datetime(ts2, 'ConvertFrom', 'posixtime');
ts3 = datetime(ts3, 'ConvertFrom', 'posixtime');
start_ts = max([min(ts1) min(ts2) min(ts3)]);
end_ts = min([max(ts1) max(ts2) max(ts3)]);
disp('Done with timestamps')
%---------------------------------------------------------

for range = MIN_STEP:1:MAX_STEP
    pm25_sp_1 = data_s1(:, 2);
    pm25_sp_2 = data_s2(:, 2);
    pm25_sp_3 = data_s3(:, 2);
    disp('Extracting data:');

    uts = start_ts:minutes(range):end_ts;
    pm25_sp_1 = samplize(ts1, pm25_sp_1, uts);
    pm25_sp_2 = samplize(ts2, pm25_sp_2, uts);
    pm25_sp_3 = samplize(ts3, pm25_sp_3, uts);
    disp('Sapmplizing data');
    % pm25_sp_1 = filter(guass_filter, 1, pm25_sp_1);
    % pm25_sp_2 = filter(guass_filter, 1, pm25_sp_2);
    % pm25_sp_3 = filter(guass_filter, 1, pm25_sp_3);

    plot(uts, pm25_sp_1, 'r', uts, pm25_sp_2, 'b', uts, pm25_sp_3, 'g')
    title('PM2.5 ug/m3 two day data: Indoor testing (Absolute error vector: sampled at 60 minute)');
    xlabel('Time: 2 Days');
    ylabel('PM2.5: measured in ug/m3');
    legend({'S1', 'S2', 'S3'},'Location','northwest')


    %--------------------------------------------------------------------
    %---------------------------- Uncomment to plot errors
    % error_bound = max_error([pm25_sp_1; pm25_sp_2; pm25_sp_3]);
    % plot(uts, error_bound, 'black', uts, abs(to_pm25_sp_1 - to_pm25_sp_2), 'r', uts, abs(to_pm25_sp_2 - to_pm25_sp_3), 'b', uts, abs(to_pm25_sp_1 - to_pm25_sp_3), 'g');
    % title('PM2.5 Error ug/m3 two day data: Indoor testing (Absolute error vector: sampled at 60 minute)');
    % xlabel('Time: 2 Days');
    % ylabel('PM2.5: measured in ug/m3');
    % legend({'allowed error', '|S1 - s2|', '|S2 - s3|', '|S3 - s1|'}, 'Location', 'northwest');

    disp('Move to next plot --')
    pause(0.5)
end