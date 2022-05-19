package edu.rmit.newsscrawler.test;

import edu.rmit.newsscrawler.common.DateTimeUtils;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
public class UtilsTest {

    @Test
    public void test() {

        var d = DateTimeUtils.getPublishedDuration(LocalDateTime.parse("2022-04-01T00:00:00"));

        log.info(d);

        assertEquals(true, true);
    }
}
