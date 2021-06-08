package org.silentsoft.datecraft;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateCraft {

    /**
     * Returns current number of milliseconds since 1970-01-01T00:00:00Z
     *
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     */
    public static long currentEpochMilli() {
        return Instant.now().toEpochMilli();
    }

    /**
     * Returns current number of seconds since 1970-01-01T00:00:00Z
     *
     * @return the number of seconds since 1970-01-01T00:00:00Z
     */
    public static long currentEpochSecond() {
        return Instant.now().getEpochSecond();
    }

    /**
     * Returns the ISO-8601 format as a Coordinated Universal Time, such as '2021-01-01T12:34:56Z'.
     *
     * @return the formatted string, not null
     */
    public static String currentDateTime() {
        return format(currentEpochMilli());
    }

    /**
     * Returns formatted date-time string based on the <code>pattern</code>.
     *
     * @param pattern the pattern to use, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static String currentDateTime(String pattern) {
        return format(currentEpochMilli(), pattern);
    }

    /**
     * Returns the ISO-8601 extended offset date-time format, such as '2021-06-07T17:30:45+09:00'.
     *
     * @param timeZone the time-zone to providing an offset information, not null
     * @return the formatted string, not null
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String currentDateTime(ZoneId timeZone) {
        return format(currentEpochMilli(), timeZone);
    }

    /**
     * Returns formatted date-time string based on the <code>pattern</code> and <code>timeZone</code>.
     *
     * @param pattern the pattern to use, not null
     * @param timeZone the time-zone to providing an offset information, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String currentDateTime(String pattern, ZoneId timeZone) {
        return format(currentEpochMilli(), pattern, timeZone);
    }

    /**
     * Returns the number of milliseconds representing the <code>dateTime</code>.
     *
     * @param dateTime the ISO-8601 format, not null
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     */
    public static long toEpochMilli(String dateTime) {
        return Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateTime)).toEpochMilli();
    }

    /**
     * Returns the number of milliseconds representing the <code>dateTime</code>.
     *
     * @param dateTime the date-time string to parse, not null
     * @param pattern the pattern to parse, not null
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static long toEpochMilli(String dateTime, String pattern) {
        return Instant.from(DateTimeFormatter.ofPattern(pattern).parse(dateTime)).toEpochMilli();
    }

    /**
     * Returns the number of milliseconds representing the <code>dateTime</code>.
     *
     * @param dateTime the date-time string to parse, not null
     * @param pattern the pattern to parse, not null
     * @param timeZone the time-zone that belongs to the date-time, not null
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static long toEpochMilli(String dateTime, String pattern, ZoneId timeZone) {
        return Instant.from(DateTimeFormatter.ofPattern(pattern).withZone(timeZone).parse(dateTime)).toEpochMilli();
    }

    /**
     * Returns the ISO-8601 format as a Coordinated Universal Time, such as '2021-01-01T12:34:56Z'.
     *
     * @param epochMilli the number of milliseconds since 1970-01-01T00:00:00Z
     * @return the formatted string, not null
     */
    public static String format(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /**
     * Returns formatted date-time string based on the <code>pattern</code>.
     *
     * @param epochMilli the number of milliseconds since 1970-01-01T00:00:00Z
     * @param pattern the pattern to use, not null
     * @return the formatted string based on the pattern, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static String format(long epochMilli, String pattern) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Returns the ISO-8601 extended offset date-time format, such as '2021-01-01T21:34:56+09:00'.
     *
     * @param epochMilli the number of milliseconds since 1970-01-01T00:00:00Z
     * @param timeZone the time-zone to providing an offset information, not null
     * @return the formatted string, not null
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(long epochMilli, ZoneId timeZone) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(timeZone));
    }

    /**
     * Returns formatted date-time string based on the <code>pattern</code> and <code>timeZone</code>.
     *
     * @param epochMilli the number of milliseconds since 1970-01-01T00:00:00Z
     * @param pattern the pattern to use, not null
     * @param timeZone the time-zone to applying an offset, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(long epochMilli, String pattern, ZoneId timeZone) {
        return Instant.ofEpochMilli(epochMilli).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(pattern).withZone(timeZone));
    }

    /**
     * Returns formatted date-time string based on the <code>toPattern</code>.
     *
     * @param dateTime the ISO-8601 format, not null
     * @param toPattern the pattern to use, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static String format(String dateTime, String toPattern) {
        return Instant.ofEpochMilli(toEpochMilli(dateTime)).atOffset(ZoneOffset.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateTime))).format(DateTimeFormatter.ofPattern(toPattern));
    }

    /**
     * Returns the ISO-8601 extended offset date-time format, such as '2021-01-01T21:34:56+09:00'.
     *
     * @param dateTime the ISO-8601 format, not null
     * @param toTimeZone the time-zone to applying an offset, not null
     * @return the formatted string, not null
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(String dateTime, ZoneId toTimeZone) {
        return Instant.ofEpochMilli(toEpochMilli(dateTime)).atZone(toTimeZone).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /**
     * Returns formatted date-time string based on the <code>toPattern</code>.
     *
     * @param dateTime the date-time string to parse, not null
     * @param fromPattern the pattern to parse, not null
     * @param toPattern the pattern to use, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static String format(String dateTime, String fromPattern, String toPattern) {
        return DateTimeFormatter.ofPattern(toPattern).format(DateTimeFormatter.ofPattern(fromPattern).parse(dateTime));
    }

    /**
     * Returns formatted date-time string based on the <code>toPattern</code> and <code>toTimeZone</code>.
     *
     * @param dateTime the ISO-8601 format, not null
     * @param toPattern the pattern to use, not null
     * @param toTimeZone the time-zone to applying an offset, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(String dateTime, String toPattern, ZoneId toTimeZone) {
        return Instant.ofEpochMilli(toEpochMilli(dateTime)).atZone(toTimeZone).format(DateTimeFormatter.ofPattern(toPattern));
    }

    /**
     * Returns formatted date-time string based on the <code>pattern</code> and <code>toTimeZone</code>.
     *
     * @param dateTime the date-time string to parse, not null
     * @param fromPattern the pattern to parse, not null
     * @param fromTimeZone the time-zone that belongs to the date-time, not null
     * @param toTimeZone the time-zone to applying an offset, not null
     * @return the formatted string, not null
     * @see #format(String, String, ZoneId, String, ZoneId)
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(String dateTime, String fromPattern, ZoneId fromTimeZone, ZoneId toTimeZone) {
        return format(dateTime, fromPattern, fromTimeZone, fromPattern, toTimeZone);
    }

    /**
     * Returns formatted date-time string based on the <code>toPattern</code> and <code>toTimeZone</code>.
     *
     * @param dateTime the date-time string to parse, not null
     * @param fromPattern the pattern to parse, not null
     * @param fromTimeZone the time-zone that belongs to the date-time, not null
     * @param toPattern the pattern to use, not null
     * @param toTimeZone the time-zone to applying an offset, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String format(String dateTime, String fromPattern, ZoneId fromTimeZone, String toPattern, ZoneId toTimeZone) {
        return Instant.ofEpochMilli(toEpochMilli(dateTime, fromPattern, fromTimeZone)).atZone(toTimeZone).format(DateTimeFormatter.ofPattern(toPattern));
    }

    /**
     * Returns the ISO-8601 format as a Coordinated Universal Time, such as '2021-01-01T12:34:56Z'.
     *
     * @param dateTime the date-time string to parse, not null
     * @param fromPattern the pattern to parse, not null
     * @return the formatted string, not null
     * @see #parse(String, String, ZoneId)
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     */
    public static String parse(String dateTime, String fromPattern) {
        return parse(dateTime, fromPattern, ZoneOffset.UTC);
    }

    /**
     * Returns the ISO-8601 format as a Coordinated Universal Time, such as '2021-01-01T12:34:56Z'.
     *
     * @param dateTime the date-time string to parse, not null
     * @param fromPattern the pattern to parse, not null
     * @param fromTimeZone the time-zone that belongs to the date-time, not null
     * @return the formatted string, not null
     * @see java.time.format.DateTimeFormatterBuilder#appendPattern(String)
     * @see java.time.ZoneId#of(String)
     * @see java.time.ZoneOffset#of(String)
     * @see java.time.ZoneOffset#ofHours(int)
     * @see java.time.ZoneOffset#ofHoursMinutes(int, int)
     */
    public static String parse(String dateTime, String fromPattern, ZoneId fromTimeZone) {
        return DateTimeFormatter.ISO_INSTANT.format(DateTimeFormatter.ofPattern(fromPattern).withZone(fromTimeZone).parse(dateTime));
    }

}
