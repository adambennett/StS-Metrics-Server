package DuelistMetrics.Server.models;

import java.util.*;

public class RunLogFilter {

    public List<Long> ids;
    public String host;
    public String character;
    public String timeStart;
    public String timeEnd;
    public String country;
    public Boolean isNonDuelist;

    public RunLogFilter() {}

    public RunLogFilter(List<Long> ids, String host, String character, String timeStart, String timeEnd, String country, Boolean isNonDuelist) {
        this.ids = ids;
        this.host = host;
        this.character = character;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.country = country;
        this.isNonDuelist = isNonDuelist;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getNonDuelist() {
        return isNonDuelist;
    }

    public void setNonDuelist(Boolean nonDuelist) {
        isNonDuelist = nonDuelist;
    }
}
