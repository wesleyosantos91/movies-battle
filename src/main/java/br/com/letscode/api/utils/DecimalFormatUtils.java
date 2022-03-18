package br.com.letscode.api.utils;

import lombok.SneakyThrows;

import java.text.DecimalFormat;

public class DecimalFormatUtils {

    @SneakyThrows
    public static Number format(String number) {
        DecimalFormat decimalFormatUtils = new DecimalFormat("#,###.00");
        return decimalFormatUtils.parse(number);
    }
}
