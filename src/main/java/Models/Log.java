package Models;

import java.sql.Timestamp;

/**
 * @Author itmei
 * @Date 2024/6/12 20:22
 * @description:
 * @Title: Log
 * @Package Models
 */
public class Log {
    private int logId;
    private String username;
    private String operation;

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                ", timestamp=" + timestamp +
                ", details='" + details + '\'' +
                '}';
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private Timestamp timestamp;
    private String details;
}
