package com.nikitakozlov.pury;

import com.nikitakozlov.pury.internal.StopWatch;

import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class StopWatchTest {

    private static long ONE_HUNDERD_MILLIS = 100L;

    @Test
    public void getExecTimeInMillis_ReturnsTimeBiggerThenTimeBetweenStartAndStop() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sleep(ONE_HUNDERD_MILLIS);
        stopWatch.stop();
        assertTrue(ONE_HUNDERD_MILLIS <= stopWatch.getExecTimeInMillis());
    }
}