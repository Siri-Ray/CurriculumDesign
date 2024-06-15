package Controller;

import Dao.BasicInfoDao;
import Dao.GraduateStudentDao;
import Dao.UserDao;
import Dao.VerifyDao;
import Models.*;
import Utils.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.SQLException;

import static Utils.OrmUtil.getEntity;
import static Utils.SM3Util.encrypt;

/**
 * @Author itmei
 * @Date 2024/6/15 1:18
 * @description:
 * @Title: reviewGraduateServlet
 * @Package Controller
 */
@WebServlet("/reviewGraduateServlet")
public class reviewGraduateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("reviewing...");
        VerifyDao verifyDao = new VerifyDao();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ReviewInfo reviewInfo = JsonUtil.parseJsonToEntity(request.getReader(), ReviewInfo.class);
        PrintWriter out = response.getWriter();


        try {
            // Parse request parameters
            String action = reviewInfo.getStatus();
            int reviewId = reviewInfo.getReviewId();

            int result = 0;
            String message = "";

            if ("approve".equals(action)) {
                VerifyInfo verifyInfo = verifyDao.searchById(reviewId);
                if (verifyInfo != null) {
                    String collegeCode = getCollegeCode(verifyInfo.getCollege());
                    String majorCode = getMajorCode(verifyInfo.getMajor());
                    String studentId = verifyDao.generateStudentId(collegeCode, majorCode);

                    // Generate password (last 6 digits of ID card)
                    String idNumber = verifyInfo.getIdNumber();
                    String password = encrypt(idNumber.substring(idNumber.length() - 6));
                    basicInformation basicInformation = new basicInformation();
                    basicInformation.setCollege(verifyInfo.getCollege());
                    basicInformation.setDegreeType(verifyInfo.getDegreeType());
                    basicInformation.setGender(verifyInfo.getGender());
                    basicInformation.setName(verifyInfo.getName());
                    basicInformation.setMajor(verifyInfo.getMajor());
                    basicInformation.setSupervisor(verifyInfo.getSupervisor());
                    basicInformation.setIdNumber(idNumber);
                    basicInformation.setStudentId(Integer.parseInt(studentId));
                    graduateStudent graduateStudent = new graduateStudent();
                    graduateStudent.setStudentId(Integer.parseInt(studentId));
                    graduateStudent.setPassword(password);
                    BasicInfoDao basicInfoDao = new BasicInfoDao();
                    basicInfoDao.addBasicInformation(basicInformation);
                    GraduateStudentDao graduateStudentDao = new GraduateStudentDao();
                    graduateStudentDao.addgraduateStudent(graduateStudent);
                    message =( result==1 ? "Review approved successfully." : "Failed to approve review.");
                    if(result == 1){
                        verifyDao.deleteById(reviewId);
                    }
                } else {
                    message = "Review ID not found.";
                }
            } else if ("reject".equals(action)) {
                result = verifyDao.deleteById(reviewId);
                message = result==1 ? "Review rejected successfully." : "Failed to reject review.";
            } else {
                message = "Invalid status.";
            }

            // Construct response JSON
            JSONObject responseJson = new JSONObject();
            responseJson.put("reviewId", reviewId);
            responseJson.put("message", message);
            responseJson.put("success", result==1 ? 1 : 0);
            out.print(responseJson.toJSONString());

        } catch (SQLException e) {
            e.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("reviewId", -1);
            responseJson.put("message", "Error: " + e.getMessage());
            responseJson.put("success", 0);
            out.print(responseJson.toJSONString());
        }
    }

    private String getCollegeCode(String college) {
        switch (college.toLowerCase()) {
            case "information":
                return "04";
            case "computer":
                return "03";
            default:
                throw new IllegalArgumentException("Unknown college: " + college);
        }
    }

    private String getMajorCode(String major) {
        switch (major.toLowerCase()) {
            case "cs":
                return "14";
            case "se":
                return "15";
            default:
                throw new IllegalArgumentException("Unknown major: " + major);
        }
    }

    public String getJsonStringFromRequest(HttpServletRequest request) throws IOException {

        StringBuffer sb = new StringBuffer() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        String str =sb.toString();
        return str;
    }


}
