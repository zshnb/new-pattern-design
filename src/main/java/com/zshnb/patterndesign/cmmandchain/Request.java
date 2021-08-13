package com.zshnb.patterndesign.cmmandchain;

import java.time.LocalDateTime;

public class Request {
    private String ip;
    private String locale;
    private LocalDateTime lastVisitAt;

    public Request(String ip, String locale, LocalDateTime lastVisitAt) {
        this.ip = ip;
        this.locale = locale;
        this.lastVisitAt = lastVisitAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocalDateTime getLastVisitAt() {
        return lastVisitAt;
    }

    public void setLastVisitAt(LocalDateTime lastVisitAt) {
        this.lastVisitAt = lastVisitAt;
    }
}
