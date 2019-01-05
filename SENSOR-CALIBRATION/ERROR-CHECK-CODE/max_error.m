function merror = max_error(signal_set)
    max_sig = max(signal_set);
    min_error = ( max_sig <= 100 ) * 10;
    per_error = (max_sig <= 500 & max_sig > 100);
    per_error = 10 * ((per_error .* max_sig) / 100);
    upper_error = 20 * (((max_sig > 500) .* max_sig) / 100);
    merror = min_error + per_error + upper_error;
 end