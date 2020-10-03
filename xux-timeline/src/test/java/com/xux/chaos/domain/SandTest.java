package com.xux.chaos.domain;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SandTest {

    @Test
    public void testCreateDateTime() {
        //定义一个任意格式的日期、l讨间字符串
        String strl = "9999-04-12 00:00:00";
        //根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //执行解析
        LocalDateTime dateTime = LocalDateTime.parse(strl, formatter);

        System.out.println(dateTime.getYear());
    }

    @Test
    public void testCreateDate() {
        //执行解析
        LocalDateTime dt3 = LocalDateTime.of(999999999,9,10,20,51,24);

        System.out.println(dt3.getYear());
    }

    @Test
    public void testMaxLong() {
        //执行解析
        System.out.println(Long.MAX_VALUE);
    }


}