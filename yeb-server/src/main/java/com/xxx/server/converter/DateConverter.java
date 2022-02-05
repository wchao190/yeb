package com.xxx.server.converter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
全局日期转换
 */
@Configurable
public class DateConverter implements Converter<String, LocalDate> {
    @Bean
    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
