package com.tiktok.utils;


public class ErrorUtil {

    public static String errorInfoToString(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Exception: " + e.getClass().getName() + "; ");
        sb.append("Message: " + e.getMessage() + "; ");
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            sb.append("at " + s + "; ");
        }
        return sb.toString();
    }
}
