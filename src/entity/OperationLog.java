package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "operation_log")
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "operation")
    private String operation;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User authority;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;


    public OperationLog(String operation, User user, LocalDate date, LocalTime time) {
        this.operation = operation;
        this.authority = user;
        this.date = date;
        this.time = time;

    }

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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String toString() {
        return
                "date:'" + date + '\'' +
                        ", time: '" + time +
                        "', operation: '" + operation +
                        "'\n";
    }
}
