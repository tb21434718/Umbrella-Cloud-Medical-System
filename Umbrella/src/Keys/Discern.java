package Keys;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.*;


public class Discern extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        int id = 0;
        Init_Keys Keys = null;
    	response.setCharacterEncoding("UTF8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    id = Integer.valueOf(item.getString());
                }
                else {
                    InputStream content = item.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(content);
                    Keys = (Init_Keys) ois.readObject();
                }
            }
            if(id!=Keys.getPwd()) {
                response.getWriter().print(0);
                return;
            }
            response.getWriter().println("身份认证通过");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Parsing file upload failed.", e);
        }

        //使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();
        //将数据存储到session中
        session.setAttribute("Keys", Keys);
        session.setAttribute("ID", Keys.getID());
        //获取session的Id



    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

}
