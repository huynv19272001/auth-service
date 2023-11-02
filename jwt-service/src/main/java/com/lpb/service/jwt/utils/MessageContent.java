package com.lpb.service.viettel.utils;

public enum MessageContent {

    SUCCESS("00", "SUCCESS"), FAIL("99", "System error"), NO_DATA("90", "Data Empty!");

    public final String label;
    public final String description;

    private MessageContent(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
