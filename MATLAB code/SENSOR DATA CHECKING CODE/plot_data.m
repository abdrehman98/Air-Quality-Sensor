% Reading data from file
com12 = csvread('../DATA/SEP-27/COM12.csv');
com13 = csvread('../DATA/SEP-27/COM13.csv');
com14 = csvread('../DATA/SEP-27/COM14.csv');


plot(com12(:, 3), com12(:, 1))

figure
plot(com13(:, 3), com13(:, 1))

figure
plot(com14(:, 3), com14(:, 1))