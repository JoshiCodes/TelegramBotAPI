package de.joshizockt.telegram.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private final String name;
    private final String pattern;
    private boolean debug;

    public Logger(String name, String pattern, boolean debug) {
        this.name = name;
        this.pattern = pattern;
        this.debug = debug;
    }

    public String prefix() {
        return "[" + new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis())) + "] ";
    }

    public Logger log(String msg) {
        System.out.println(prefix() + msg);
        return this;
    }

    public Logger log(int type, String msg) {
        LogType logType = null;
        for(LogType types : LogType.values()) {
            if(type == types.i) {
                logType = types;
                break;
            }
        }
        if(logType == null) logType = LogType.NORMAL;
        return log(logType, msg);
    }

    public Logger log(LogType type, String msg) {
        if(type == LogType.DEBUG && !debug) return this;
        String pr = "";
        if(type != LogType.NORMAL) {
            pr = "[" + type.name() + "] ";
        }
        System.out.println(prefix() + pr + msg);
        return this;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isDebug() {
        return debug;
    }

    public enum LogType {
        DEBUG(-1), NORMAL(0), INFO(1), ERROR(2), WARNING(3);
        int i;
        LogType(int i) {
            this.i = i;
        }
    }

}
