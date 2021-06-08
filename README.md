# DateCraft

![release](https://img.shields.io/badge/release-v1.0.0-blue.svg)
[![Build Status](https://travis-ci.com/silentsoft/datecraft.svg?branch=main)](https://travis-ci.com/silentsoft/datecraft)

> Facade API to handling various date formats for Coordinated Universal Time also known as UTC.

`DateCraft` is a simple facade API to handling various date formats for `Coordinated Universal Time` also known as `UTC`.

## Maven Central
```xml
<dependencies>
    <dependency>
        <groupId>org.silentsoft</groupId>
        <artifactId>datecraft</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Epoch millisecond examples
  - Getting current epoch millisecond
    ```java
    DateCraft.currentEpochMilli();
    ```
  - Getting epoch millisecond from `ISO-8601` format
    ```java
    DateCraft.toEpochMilli("2021-01-01T12:34:56Z");
    ```
  - Getting epoch millisecond from specific date time with pattern
    ```java
    DateCraft.toEpochMilli("2021-01-01 12:34:56", "uuuu-MM-dd HH:mm:ss");
    ```
  - Getting epoch millisecond from specific date time with pattern and time-zone
    ```java
    DateCraft.toEpochMilli("2021-01-01 12:34:56", "uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```

## Current date-time examples
  - Getting current date-time as a `Coordinated Universal Time` in `ISO-8601` format
    ```java
    DateCraft.currentDateTime();
    ```
  - Getting current date-time as a `Coordinated Universal Time` with specific pattern
    ```java
    DateCraft.currentDateTime("uuuu-MM-dd HH:mm:ss");
    ```
  - Getting current date-time with specific time-zone in `ISO-8601` format
    ```java
    DateCraft.currentDateTime(ZoneId.of("GMT+9"));
    ```
  - Getting current date-time with specific pattern and time-zone
    ```java
    DateCraft.currentDateTime("uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```

## Time-zone examples
  - Getting UTC
    ```java
    ZoneId timeZone = ZoneOffset.UTC;
    ```
  - Getting Asia/Seoul
    ```java
    ZoneId timeZone = ZoneId.of("Asia/Seoul");
    ```
  - Getting GMT+9
    ```java
    ZoneId timeZone = ZoneId.of("GMT+9");
    ```
  - Getting +9 offset hours (equals to `GMT+9`)
    ```java
    ZoneId timeZone = ZoneOffset.ofHours(9);
    ```

## Formatting examples
  - format(long epochMilli)
    ```java
    // Returns "2021-01-01T12:34:56Z"
    DateCraft.format(1609504496000L);
    ```
  - format(long epochMilli, String pattern)
    ```java
    // Returns "2021-01-01 12:34:56"
    DateCraft.format(1609504496000L, "uuuu-MM-dd HH:mm:ss");
    ```
  - format(long epochMilli, ZoneId timeZone)
    ```java
    // Returns "2021-01-01T21:34:56+09:00"
    DateCraft.format(1609504496000L, ZoneId.of("GMT+9"));
    ```
  - format(long epochMilli, String pattern, ZoneId timeZone)
    ```java
    // Returns "2021-01-01 21:34:56"
    DateCraft.format(1609504496000L, "uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```
  - format(String dateTime, String toPattern)
    ```java
    // Returns "2021-01-01 12:34:56"
    DateCraft.format("2021-01-01T12:34:56Z", "uuuu-MM-dd HH:mm:ss");
    ```
  - format(String dateTime, ZoneId toTimeZone)
    ```java
    // Returns "2021-01-01T21:34:56+09:00"
    DateCraft.format("2021-01-01T12:34:56Z", ZoneId.of("GMT+9"));
    ```
  - format(String dateTime, String fromPattern, String toPattern)
    ```java
    // Returns "2021-01-01 12:34:56"
    DateCraft.format("01 Jan 2021, 12:34:56", "dd MMM uuuu, HH:mm:ss", "uuuu-MM-dd HH:mm:ss");
    ```
  - format(String dateTime, String toPattern, ZoneId toTimeZone)
    ```java
    // Returns "2021-01-01 21:34:56"
    DateCraft.format("2021-01-01T12:34:56Z", "uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```
  - format(String dateTime, String fromPattern, ZoneId fromTimeZone, ZoneId toTimeZone)
    ```java
    // Returns "2021-01-01 21:34:56"
    DateCraft.format("2021-01-01 12:34:56", "uuuu-MM-dd HH:mm:ss", ZoneOffset.UTC, ZoneId.of("GMT+9"));
    ```
  - format(String dateTime, String fromPattern, ZoneId fromTimeZone, String toPattern, ZoneId toTimeZone)
    ```java
    // Returns "2021-01-01 21:34:56"
    DateCraft.format("01 Jan 2021, 12:34:56", "dd MMM uuuu, HH:mm:ss", ZoneOffset.UTC, "uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```

## Parsing examples
  - parse(String dateTime, String fromPattern)
    ```java
    // Returns "1970-01-01T00:00:00Z"
    DateCraft.parse("1970-01-01 00:00:00", "uuuu-MM-dd HH:mm:ss");
    ```
  - parse(String dateTime, String fromPattern, ZoneId fromTimeZone)
    ```java
    // Returns "1970-01-01T00:00:00Z"
    DateCraft.parse("1970-01-01 09:00:00", "uuuu-MM-dd HH:mm:ss", ZoneId.of("GMT+9"));
    ```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please note we have a [CODE_OF_CONDUCT](https://github.com/silentsoft/datecraft/blob/main/CODE_OF_CONDUCT.md), please follow it in all your interactions with the project.

## License
Please refer to [LICENSE](https://github.com/silentsoft/datecraft/blob/main/LICENSE.txt).
