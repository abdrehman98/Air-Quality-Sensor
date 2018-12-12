function samplized = samplize(ts, signal, uts)
    samplized = zeros(size(uts));

    for index = 1:length(uts) - 1
        lower = uts(index);
        upper = uts(index + 1);
        logic_indicies = (ts >= lower & ts < upper);
        samplized(index) = mean(signal(logic_indicies));
    end
    samplize(length(samplize) ) = samplize(length(samplize) - 1)
end