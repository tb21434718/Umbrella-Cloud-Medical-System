package handle;

import Keys.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;


public class HandleUpload extends javax.servlet.http.HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/web?useSSL=true&characterEncoding=UTF8";

    static final String ACC = "root";
    static final String PWD = "zxczxc";


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if(session==null) {
            response.getWriter().println(0);//错误代码
            return;
        }
        int ID = (int)session.getAttribute("ID");   // 医院编号
        String hospital_table = String.valueOf(ID);
        Init_Keys Keys = (Init_Keys) session.getAttribute("Keys");
        Key k1 = Keys.getKey();
        SecretKeySpec k2 = Keys.getKeyAes();

        Connection conn;
        Statement stat;
        // -- AES加密
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String keshi = request.getParameter("keshi");
        String check_in_time = request.getParameter("check_in_time");
        String main_doctor = request.getParameter("main_doctor");
        String diagnose_time = request.getParameter("diagnose_time");
        String last_diagnose = request.getParameter("last_diagnose");
        String address = request.getParameter("address");
        String now_illness = request.getParameter("now_illness");
        String treatment = request.getParameter("treatment");
        try {
            name = EncryptData.EncryptAes(k2, name);
            sex = EncryptData.EncryptAes(k2, sex);
            id = EncryptData.EncryptAes(k2, id);
            status = EncryptData.EncryptAes(k2, status);
            keshi = EncryptData.EncryptAes(k2, keshi);
            check_in_time = EncryptData.EncryptAes(k2, check_in_time);
            main_doctor = EncryptData.EncryptAes(k2, main_doctor);
            diagnose_time = EncryptData.EncryptAes(k2, diagnose_time);
            last_diagnose = EncryptData.EncryptAes(k2, last_diagnose);
            address = EncryptData.EncryptAes(k2, address);
            now_illness = EncryptData.EncryptAes(k2, now_illness);
            treatment = EncryptData.EncryptAes(k2, treatment);
        } catch (Exception e) {}
        // -- 全同态加密
        String age = request.getParameter("age");
        String agePlus = EncryptData.EncryptPlus(k1, age);
        String ageMult = EncryptData.EncryptMulti(k1, age);
        String total_cost = request.getParameter("total_cost");
        String total_costPlus = EncryptData.EncryptPlus(k1, total_cost);
        String total_costMult = EncryptData.EncryptMulti(k1, total_cost);
        String ill_last = request.getParameter("ill_last");
        String ill_lastPlus = EncryptData.EncryptPlus(k1, ill_last);
        String ill_lastMult = EncryptData.EncryptMulti(k1, ill_last);

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,ACC,PWD);
            stat = conn.createStatement();
            String query = "INSERT INTO `"+hospital_table+"` VALUES (\""+name+"\",\""+sex+"\",\""+agePlus+"\",\""+ageMult+"\",\""+id+"\",\""+status+
                    "\",\""+keshi+"\",\""+check_in_time+"\",\""+main_doctor+"\",\""+diagnose_time+"\",\""+last_diagnose+
                    "\",\""+total_costPlus+"\",\""+total_costMult+"\",\""+ill_lastPlus+"\",\""+ill_lastMult+"\",\""+address+"\",\""+now_illness+"\",\""+treatment+"\")";
            stat.executeUpdate(query);
            File f = new File("/tmp/log.txt");
            PrintStream ps = new PrintStream(new FileOutputStream(f));
            ps.println(query);
        } catch (Exception expt) {
        }
    }
}
