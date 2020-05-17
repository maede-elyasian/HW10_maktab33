package entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OperationLog {
    private String operation;
    private User authority;
    private LocalDate date;
    private Time time;

    public OperationLog() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public User getAuthority() {
        return authority;
    }

    public void setAuthority(User authority) {
        this.authority = authority;
    }

    public Date getDate() {
        return Date.valueOf(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        DateTimeFormatter ob = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeformat = time.format(ob);
        this.time = Time.valueOf(timeformat);
    }

    public String toString() {
        return
                        "date:'" + date + '\'' +
                        ", time: '" + time +
                        "', operation: '" + operation +
                        "'\n";
    }
}
