package ru.progwards.java1.lessons.datetime;

import java.time.*;
import static java.time.temporal.ChronoUnit.*;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}
    private ZonedDateTime start;
    private Duration duration;
    public Insurance(ZonedDateTime start) {
        this.start = start;
    }
    public Insurance(String strStart, FormatStyle style) {
        try {
            switch (style) {
                case SHORT:
                    this.start = ZonedDateTime.of(LocalDate.parse(strStart), LocalTime.parse("00:00:00.0"), ZoneId.systemDefault());
                    break;
                case LONG:
                    this.start = ZonedDateTime.of(LocalDateTime.parse(strStart), ZoneId.systemDefault());
                    break;
                case FULL:
                    this.start = ZonedDateTime.parse(strStart);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    public void setDuration(ZonedDateTime expiration) {
        this.duration = Duration.between(start, expiration);
    }
    public void setDuration(int months, int days, int hours) {
        ZonedDateTime exp = start.plusMonths(months).plusDays(days).plusHours(hours);
        this.duration = Duration.between(start, exp);
    }
    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {
            case SHORT:
                this.duration = Duration.between(start, start.plus(Long.valueOf(strDuration), MILLIS));
                break;
            case LONG:
                LocalDateTime ldtExp = LocalDateTime.parse(strDuration);
                ZonedDateTime exp = start.plusYears(ldtExp.getYear()).plusMonths(ldtExp.getMonthValue()).
                        plusDays(ldtExp.getDayOfMonth()).plusHours(ldtExp.getHour()).
                        plusMinutes(ldtExp.getMinute()).plusSeconds(ldtExp.getSecond());
                this.duration = Duration.between(start, exp);
                break;
            case FULL:
                this.duration = Duration.parse(strDuration);
                break;
        }
    }
    public boolean checkValid(ZonedDateTime dateTime) {
        if (start.isAfter(dateTime)) return false;
        if (duration != null && !duration.isZero()) {
            if (start.plus(duration).isBefore(dateTime)) return false;
        }
        return true;
    }
    public String toString() {
        String validStr = "";
        if (checkValid(ZonedDateTime.now())) {
            validStr = " is valid";
        } else {
            validStr = " is not valid";
        }
        return "Insurance issued on " + start + validStr;
    }

    public static void main(String[] args) {
        Insurance ins = new Insurance(ZonedDateTime.parse("2018-07-17T22:50:14.073272+03:00[Europe/Moscow]"));
        ins.setDuration(ZonedDateTime.parse("2018-07-17T22:50:14.073272+03:00[Europe/Moscow]"));
        System.out.println(ins.duration);
        System.out.println(Duration.ZERO);
        System.out.println(ins.start.plus(ins.duration));
        System.out.println(ins.checkValid(ZonedDateTime.parse("2024-01-07T22:50:14.073322+03:00[Europe/Moscow]")));
        System.out.println(ins);
    }
}