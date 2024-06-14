package Models;

/**
 * @Author itmei
 * @Date 2024/6/14 18:39
 * @description:
 * @Title: basicInformation
 * @Package Models
 */
public class basicInformation {
    private int studentId;
    private String name;
    private String gender;
    private String idNumber;
    private String department;

    @Override
    public String toString() {
        return "basicInformation{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", degreeType='" + degreeType + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    private String major;
    private String degreeType;
    private String supervisor;
}
