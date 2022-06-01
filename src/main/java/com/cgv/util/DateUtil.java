package com.cgv.util;

import java.time.LocalDate;

public abstract class DateUtil {
    public static int getAge(LocalDate birthDate) {
        return LocalDate.now().getYear() - birthDate.getYear() + 1;
    }
}
