package org.silentsoft.datecraft;

import org.junit.Assert;
import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateCraftTest {

    @Test
    public void currentTest() {
        Assert.assertTrue(Math.abs(DateCraft.currentEpochMilli() - System.currentTimeMillis()) < 1000);
        Assert.assertTrue(Math.abs((DateCraft.currentEpochSecond() * 1000) - DateCraft.currentEpochMilli()) < 1000);

        Assert.assertNotNull(DateCraft.currentDateTime());
        Assert.assertNotNull(DateCraft.currentDateTime(ZoneId.of("Asia/Seoul")));
        Assert.assertNotNull(DateCraft.currentDateTime("dd MMM yyyy, HH:mm:ss"));
        Assert.assertNotNull(DateCraft.currentDateTime("dd MMM yyyy, HH:mm:ss", ZoneId.of("Asia/Seoul")));
    }

    @Test
    public void toEpochMilliTest() {
        {
            long epochMilli = LocalDateTime.of(1970, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC) * 1000;

            Assert.assertEquals(0, epochMilli);

            // No timezone provided
            Assert.assertThrows(DateTimeException.class, () -> DateCraft.toEpochMilli("1970-01-01T00:00:00"));

            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01T00:00:00Z"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("01 Jan 1970, 00:00:00Z", "dd MMM yyyy, HH:mm:ssXXX"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01T00:00:00Z", "yyyy-MM-dd'T'HH:mm:ssXXX"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("19700101000000", "yyyyMMddHHmmss", ZoneOffset.UTC));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC));

            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01T09:00:00+09:00"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("01 Jan 1970, 09:00:00+09:00", "dd MMM yyyy, HH:mm:ssXXX"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01T09:00:00+09:00", "yyyy-MM-dd'T'HH:mm:ssXXX"));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("19700101090000", "yyyyMMddHHmmss", ZoneOffset.ofHours(9)));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01 09:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.ofHours(9)));
        }
        {
            long epochMilli = DateCraft.toEpochMilli("1970-01-01 09:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.ofHours(9));

            // No timezone provided
            Assert.assertThrows(DateTimeException.class, () -> DateCraft.toEpochMilli("19700101000000", "yyyyMMddHHmmss"));
            Assert.assertThrows(DateTimeException.class, () -> DateCraft.toEpochMilli("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));

            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("19700101000000", "yyyyMMddHHmmss", ZoneOffset.UTC));
            Assert.assertEquals(epochMilli, DateCraft.toEpochMilli("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC));
        }
    }

    @Test
    public void formatTest() {
        {
            long epochMilli = LocalDateTime.of(1970, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC) * 1000;
            Assert.assertEquals("1970-01-01T00:00:00Z", DateCraft.format(epochMilli));
            Assert.assertEquals("1970-01-01T09:00:00+09:00", DateCraft.format(epochMilli, ZoneOffset.ofHours(9)));

            Assert.assertEquals("1970-01-01 00:00:00", DateCraft.format(epochMilli, "yyyy-MM-dd HH:mm:ss"));
            Assert.assertEquals("1970-01-01 09:00:00", DateCraft.format(epochMilli, "yyyy-MM-dd HH:mm:ss", ZoneOffset.ofHours(9)));
        }
        {
            long epochMilli = LocalDateTime.of(1970, 1, 1, 9, 0, 0).toEpochSecond(ZoneOffset.ofHours(9)) * 1000;
            Assert.assertEquals("1970-01-01T00:00:00Z", DateCraft.format(epochMilli));
            Assert.assertEquals("1970-01-01T09:00:00+09:00", DateCraft.format(epochMilli, ZoneOffset.ofHours(9)));

            Assert.assertEquals("1970-01-01 00:00:00", DateCraft.format(epochMilli, "yyyy-MM-dd HH:mm:ss"));
            Assert.assertEquals("1970-01-01 09:00:00", DateCraft.format(epochMilli, "yyyy-MM-dd HH:mm:ss", ZoneOffset.ofHours(9)));
        }

        Assert.assertEquals("1970-01-01T09:00:00+09:00", DateCraft.format("1970-01-01T00:00:00Z", ZoneOffset.ofHours(9)));
        Assert.assertEquals("1970-01-01T09:00:00+09:00", DateCraft.format("1970-01-01T00:00:00Z", ZoneId.of("Asia/Seoul")));
        Assert.assertEquals("1970-01-01T09:00:00+09:00", DateCraft.format("1970-01-01T00:00:00Z", ZoneId.of("GMT+9")));

        Assert.assertEquals("1970-01-01 00:00:00", DateCraft.format("1970-01-01T00:00:00Z", "yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("1970-01-01 09:00:00", DateCraft.format("1970-01-01T09:00:00+09:00", "yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("01 Jan 1970, 00:00:00", DateCraft.format("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", "dd MMM yyyy, HH:mm:ss"));

        Assert.assertEquals("01 Jan 1970, 09:00:00", DateCraft.format("1970-01-01T00:00:00Z", "dd MMM yyyy, HH:mm:ss", ZoneOffset.ofHours(9)));
        Assert.assertEquals("1970-01-01 09:00:00", DateCraft.format("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC, ZoneOffset.ofHours(9)));

        Assert.assertEquals("01 Jan 1970, 09:00:00", DateCraft.format("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC, "dd MMM yyyy, HH:mm:ss", ZoneOffset.ofHours(9)));
    }

    @Test
    public void parseTest() {
        Assert.assertEquals("1970-01-01T00:00:00Z", DateCraft.parse("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        Assert.assertEquals("1970-01-01T00:00:00Z", DateCraft.parse("1970-01-01 09:00:00", "yyyy-MM-dd HH:mm:ss", ZoneOffset.ofHours(9)));
    }

}
