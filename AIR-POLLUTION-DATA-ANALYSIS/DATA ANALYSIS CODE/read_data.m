function [ file_data ] = read_data(file_path)
file_id = fopen(file_path);

data = [];
n = 0;
while true
    line = fgetl(file_id);
    if ~ischar(line)
        break;
    end
    disp(line)
    data = [data ; strsplit(line, ',')];
    n = n + 1;
end
disp('Number of lines read:');
disp(n);

fclose(file_id);
file_data = data;
end