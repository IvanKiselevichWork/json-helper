package com.epam;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TimeTest {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2021, Month.FEBRUARY, 11);
        LocalDate end = LocalDate.of(2021, Month.FEBRUARY, 16);

        LocalDate date1 = LocalDate.of(2021, Month.FEBRUARY, 10);
        LocalDate date2 = LocalDate.of(2021, Month.FEBRUARY, 11);
        LocalDate date3 = LocalDate.of(2021, Month.FEBRUARY, 12);
        LocalDate date4 = LocalDate.of(2021, Month.FEBRUARY, 15);
        LocalDate date5 = LocalDate.of(2021, Month.FEBRUARY, 16);
        LocalDate date6 = LocalDate.of(2021, Month.FEBRUARY, 17);
        test(!checkTime(start, end, date1), 1);
        test(checkTime(start, end, date2), 2);
        test(checkTime(start, end, date3), 3);
        test(checkTime(start, end, date4), 4);
        test(checkTime(start, end, date5), 5);
        test(!checkTime(start, end, date6), 6);

        Integer i = 1;
        //Class<Integer> clazz = i.getClass();
        Class<? extends Integer> clazz = i.getClass();

        Number[] numbers = new Integer[10];
        //numbers[0] = 8.9; // exception java.lang.ArrayStoreException

        //List<Number> numbers1 = new ArrayList<Integer>();
        List<Integer> integers = new ArrayList<Integer>();
        List list = integers;
        List<Number> numbers1 = list;
        numbers1.add(9.8);
        //Integer integer = integers.get(0); // java.lang.ClassCastException
    }

    public void run(List<String> ... lists) {
        System.out.println(lists.length);
    }

    public static boolean checkTime(LocalDate start, LocalDate end, LocalDate date) {
        if (!start.isAfter(date) && !end.isBefore(date)) {
            return true;
        } else {
            return false;
        }
    }

    public static void test(boolean condition, int n) {
        if (condition) {
            System.out.println("PASSED! " + n);
        } else {
            System.out.println("FAILED!" + n);
        }
    }
}
