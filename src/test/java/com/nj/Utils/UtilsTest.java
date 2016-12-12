package com.nj.Utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.nj.app.Application;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UtilsTest {

    @Test
    public void testUtilsLog() throws InterruptedException {

        Logger utilsLogger = (Logger) LoggerFactory.getLogger("com.nj.utils");

        final Appender mockAppender = mock(Appender.class);
        when(mockAppender.getName()).thenReturn("MOCK");
        utilsLogger.addAppender(mockAppender);

        final List<String> capturedLogs = Collections.synchronizedList(new ArrayList<>());
        final CountDownLatch latch = new CountDownLatch(3);

        //Capture logs
        doAnswer((invocation) -> {
            LoggingEvent loggingEvent = invocation.getArgumentAt(0, LoggingEvent.class);
            capturedLogs.add(loggingEvent.getFormattedMessage());
            latch.countDown();
            return null;
        }).when(mockAppender).doAppend(any());

        //Call method which will do logging to be tested
        Application.main(null);

        //Wait 5 seconds for latch to be true. That means 3 log lines were logged
        assertThat(latch.await(5L, TimeUnit.SECONDS), is(true));

        //Now assert the captured logs
        assertThat(capturedLogs, hasItem(containsString("One")));
        assertThat(capturedLogs, hasItem(containsString("Two")));
        assertThat(capturedLogs, hasItem(containsString("Three")));
    }
}
