>> csvread('AV.csv');
>> av_data = csvread('AV.csv');
>> pms_data = csvread('s1.csv');

>> av_pm25 = av_data(:,3);
>> pms_pm25 = pms_data(:, 5);
 
>> x = av_data(:, 1);

>> valid_index = (pms_pm25 >= 0);
>> x = x(valid_index);
>> av_pm25 = av_pm25(valid_index); 
>> pms_pm25 = pms_pm25(valid_index);
 
>> pms_pm25 = [0; 0; 0; 0; 0; 0; 0; pms_pm25];
>> av_pm25 = [0; 0; 0; 0; 0; 0; 0; av_pm25];
>> x = [0; 0; 0; 0; 0; 0; 0; x];
