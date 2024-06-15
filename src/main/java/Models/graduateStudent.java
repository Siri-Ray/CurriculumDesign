package Models;

import java.sql.Timestamp;

/**
 * @Author itmei
 * @Date 2024/6/14 18:36
 * @description:
 * @Title: graduateStudent
 * @Package Models
 */
public class graduateStudent {
    private int studentId;
    private String password;

    public graduateStudent(String studentId, String password, Timestamp lastPasswordChangeTime, Timestamp lastLoginAttemptTime, int loginAttemptCount) {
        this.studentId = Integer.parseInt(studentId);this.password = password;this.lastPasswordChangeTime = lastPasswordChangeTime;this.lastLoginAttemptTime = lastLoginAttemptTime;this.loginAttemptCount = loginAttemptCount;
    }

    public graduateStudent() {

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastPasswordChangeTime() {
        return lastPasswordChangeTime;
    }

    public void setLastPasswordChangeTime(Timestamp lastPasswordChangeTime) {
        this.lastPasswordChangeTime = lastPasswordChangeTime;
    }

    public Timestamp getLastLoginAttemptTime() {
        return lastLoginAttemptTime;
    }

    public void setLastLoginAttemptTime(Timestamp lastLoginAttemptTime) {
        this.lastLoginAttemptTime = lastLoginAttemptTime;
    }

    public int getLoginAttemptCount() {
        return loginAttemptCount;
    }

    public void setLoginAttemptCount(int loginAttemptCount) {
        this.loginAttemptCount = loginAttemptCount;
    }

    Timestamp lastPasswordChangeTime;
    Timestamp lastLoginAttemptTime;
    private int loginAttemptCount;

    @Override
    public String toString() {
        return "graduateStudent{" +
                "studentId=" + studentId +
                ", password='" + password + '\'' +
                ", lastPasswordChangeTime=" + lastPasswordChangeTime +
                ", lastLoginAttemptTime=" + lastLoginAttemptTime +
                ", loginAttemptCount=" + loginAttemptCount +
                '}';
    }
}
