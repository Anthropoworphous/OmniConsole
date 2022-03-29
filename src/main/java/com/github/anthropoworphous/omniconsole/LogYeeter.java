package com.github.anthropoworphous.omniconsole;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "Log4JAppender", category = "Core", elementType = "appender", printObject = true)
public class LogYeeter extends AbstractAppender {
    public LogYeeter() {
        super("OmniConsole",
                null,
                PatternLayout.newBuilder().withPattern("[%d{HH:mm:ss} %level]: %msg").build(),
                true,
                null);
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public void append(LogEvent e) {
        OmniConsole.sendLog(e.getMessage().getFormattedMessage());
    }
}
