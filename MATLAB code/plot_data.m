% Reading data from file
com12 = csvread('data/COM12.csv');
com13 = csvread('data/COM13.csv');
com14 = csvread('data/COM14.csv');

% Only fetching value of PM-1.0
com13 = com13(2:2:end);
com14 = com14(2:2:end);
com12 = com12(2:2:end);

plot(com13)