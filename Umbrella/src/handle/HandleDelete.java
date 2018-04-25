package handle;

import Keys.EncryptData;
import Keys.Init_Keys;
import Keys.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class HandleDelete extends javax.servlet.http.HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/web?useSSL=true";

    static final String ACC = "root";
    static final String PWD = "zxczxc";



    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Connection conn;
        Statement stat;

        HttpSession session;
        session = request.getSession(false);
        if(session==null) {
            response.getWriter().println(0); //错误代码
            return;
        }
        int ID = (int)session.getAttribute("ID");   // 医院编号
        String hospital_table = String.valueOf(ID);
        Init_Keys Keys = (Init_Keys) session.getAttribute("Keys");
        SecretKeySpec k2 = Keys.getKeyAes();

        String input_id = request.getParameter("id");
        try {
            input_id = EncryptData.EncryptAes(k2, input_id);
        } catch (Exception e) {}


        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,ACC,PWD);
            stat = conn.createStatement();
            String query = "delete from `"+hospital_table+"` where id=\""+input_id+"\"";
            stat.executeUpdate(query);
            } catch (Exception expt) {}
    }
}
