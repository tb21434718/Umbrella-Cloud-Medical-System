package handle;

import Keys.DecryptData;
import Keys.EncryptData;
import Keys.Init_Keys;
import Keys.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;

public class HandleDownload extends javax.servlet.http.HttpServlet {

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

        HttpSession session;
        session = request.getSession(false);
        if(session==null) {
            response.getWriter().println(0);//错误代码
            return;
        }

        int ID = (int)session.getAttribute("ID");   // 医院编号
        String hospital_table = String.valueOf(ID);
        Init_Keys Keys = (Init_Keys) session.getAttribute("Keys");
        Key k1 = Keys.getKey();
        SecretKeySpec k2 = Keys.getKeyAes();

        String input_id = request.getParameter("id");
        try {
            input_id = EncryptData.EncryptAes(k2, input_id);
        } catch (Exception e) {}


        Connection conn;
        Statement stat;

        String result = null;
        ResultSet resultset = null;
        String name=null,id=null,sex=null,situation=null,keshi=null,intime=null,doctor=null,times=null,lasttime=null,addr=null,nowill=null,treatment=null,age=null,cost=null,last=null;


        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,ACC,PWD);
            stat = conn.createStatement();
            String query = "select * from `"+hospital_table+"` where id=\""+input_id+"\"";
            resultset = stat.executeQuery(query);
            while(resultset.next()) {
                name = resultset.getString("name");
                id = resultset.getString("id");
                sex = resultset.getString("sex");
                situation = resultset.getString("status");
                keshi = resultset.getString("keshi");
                intime = resultset.getString("check_in_time");
                doctor = resultset.getString("main_doctor");
                times = resultset.getString("diagnose_time");
                lasttime = resultset.getString("last_diagnose");
                addr = resultset.getString("address");
                nowill = resultset.getString("now_illness");
                treatment = resultset.getString("treatment");
                    name = DecryptData.DecryptAes(k2, name);
                    id = DecryptData.DecryptAes(k2, id);
                    sex = DecryptData.DecryptAes(k2, sex);
                    situation = DecryptData.DecryptAes(k2, situation);
                    keshi = DecryptData.DecryptAes(k2, keshi);
                    intime = DecryptData.DecryptAes(k2, intime);
                    doctor = DecryptData.DecryptAes(k2, doctor);
                    times = DecryptData.DecryptAes(k2, times);
                    lasttime = DecryptData.DecryptAes(k2, lasttime);
                    addr = DecryptData.DecryptAes(k2, addr);
                    nowill = DecryptData.DecryptAes(k2, nowill);
                    treatment = DecryptData.DecryptAes(k2, treatment);
                result = "name#"+name;
                result += "@id#"+id;
                result += "@sex#"+sex;
                age = resultset.getString("agePlus");
                age = DecryptData.DecryptPlus(k1, age);
                result += "@age#"+age;
                result += "@situation#"+situation;
                result += "@keshi#"+keshi;
                result += "@intime#"+intime;
                result += "@doctor#" + doctor;
                result += "@times#" + times;
                result += "@lasttime#" + lasttime;
                cost = resultset.getString("total_costPlus");
                cost = DecryptData.DecryptPlus(k1, cost);
                result += "@cost#" + cost;
                last = resultset.getString("ill_lastPlus");
                last = DecryptData.DecryptPlus(k1, last);
                result += "@last#" + last;
                result += "@addr#" + addr;
                result += "@nowill#" + nowill;
                result += "@treatment#" + treatment;
            }
        } catch (Exception expt) {
        }

        File file = new File("/var/lib/tomcat8/webapps/ROOT/temp_file/result.txt");

        PrintStream ps = new PrintStream(new FileOutputStream(file));

        ps.println("Name: "+name);

        ps.println("ID Card: "+id);
        
        ps.println("Sex: "+sex);
        
        ps.println("Age: "+age);
        
        ps.println("Decease: "+situation);
        
        ps.println("Clinical Department: "+keshi);
        
        ps.println("Time to be hospitalized: "+intime);
        
        ps.println("Doctor in charge: "+doctor);
        
        ps.println("Times of seeing a doctor: "+times);
        
        ps.println("Last time to see a doctor: "+lasttime);
        
        ps.println("Total Cost: "+cost);
        
        ps.println("Lasting Time: "+last);
        
        ps.println("Address: "+addr);
        
        ps.println("Medical History: "+nowill);
        
        ps.println("Situation: "+treatment);
        
        ps.close();
        response.getWriter().print(result);//用这个返回给服务器信息

    }
}
