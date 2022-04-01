package com.github.anthropoworphous.omniconsole;

import com.github.anthropoworphous.omniconsole.jettystuff.SocketHandler;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogYeeter {
    public static void startYeeting() {
        Logger.getLogger("Minecraft").addHandler(new Handler() {
            @Override public void publish(LogRecord record) {
                SocketHandler.sendMsg(record.getMessage());
            }
            @Override public void flush() {} //nah
            @Override public void close() throws SecurityException {} //screw this
        });
    }
}
