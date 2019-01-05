TIME_STAMP_INDEX = 7;
GUASS_FILTER_WINDOW_SIZE = 10;
MIN_STEP = 1;
MAX_STEP = 5;

% DATA_VAL = 2;
% LABLE_Y = 'PM2.5: measured in ug/m3';
% DATA_VAL = 3;
% LABLE_Y = 'PM10.0: measured in ug/m3';
% DATA_VAL = 1;
% LABLE_Y = 'PM1.0: measured in ug/m3';
% LABLE_Y = 'SP';
LABLE_Y = 'Enviroment';

% ------------------------------------- To apply filter on data
% created_filter = gausswin(GUASS_FILTER_WINDOW_SIZE) ./ GUASS_FILTER_WINDOW_SIZE;
% created_filter  = (1/GUASS_FILTER_WINDOW_SIZE) * ones(1,GUASS_FILTER_WINDOW_SIZE);

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

    % pm1 = data_s1(:, 1);
    % pm2 = data_s2(:, 2);
    % pm3 = data_s3(:, 3);
    pm1 = data_s1(:, 4);
    pm2 = data_s2(:, 5);
    pm3 = data_s3(:, 6);
    % pm1 = data_s1(:, DATA_VAL);
    % pm2 = data_s2(:, DATA_VAL);
    % pm3 = data_s3(:, DATA_VAL);
    disp('Extracting data:');
    
    uts = start_ts:minutes(range):end_ts;
    pm1 = samplize(ts1, pm1, uts);
    pm2 = samplize(ts2, pm2, uts);
    pm3 = samplize(ts3, pm3, uts);
    disp('Sapmplizing data');

    % pm25_sp_1 = filter(created_filter, 1, pm25_sp_1);
    % pm25_sp_2 = filter(created_filter, 1, pm25_sp_2);
    % pm25_sp_3 = filter(created_filter, 1, pm25_sp_3);
    % disp('Apply filter');

    plot(uts, pm1, 'r', uts, pm2, 'b', uts, pm3, 'g');
    title(strcat('Two day data: Indoor testing (Absolute error vector: sampled at (', num2str(range), ' minute)'));
    xlabel('Time: 2 Days');
    ylabel(LABLE_Y);
    legend({'S1', 'S2', 'S3'},'Location','northwest')
    
    %--------------------------------------------------------------------
    %---------------------------- Uncomment to plot errors
    % error_bound = max_error([pm1; pm2; pm3]);
    % plot(uts, error_bound, 'black', uts, abs(pm1 - pm2), 'r', uts, abs(pm2 - pm3), 'b', uts, abs(pm1 - pm3), 'g');
    % title('Error ug/m3 two day data: Indoor testing (Absolute error vector: sampled at 60 minute)');
    % xlabel('Time: 2 Days');
    % ylabel(LABLE_Y);
    % legend({'allowed error', '|S1 - s2|', '|S2 - s3|', '|S3 - s1|'}, 'Location', 'northwest');

    disp('Move to next plot --')
    pause(0.5)
end