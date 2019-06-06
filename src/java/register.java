
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
             response.setContentType("text/html;charset=UTF-8");
        String s2=request.getParameter("sname");
        String s3=request.getParameter("sclass");
        int s1=Integer.parseInt(request.getParameter("sid"));
        PrintWriter out = response.getWriter();
        try {
            getconnect(s1,s2,s3);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
           out.println("Record Saved Successfully<br>");
             out.println("Hello "+s2+"Your StudentId is"+s1+" Your Class is "+s3);
              
        }

    private void getconnect(int s1, String s2, String s3) throws ClassNotFoundException, SQLException {
          Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college1","root","ankit")) {
       
            PreparedStatement st=con.prepareStatement("insert into student value(?,?,?);");
            st.setInt(1,s1);
            st.setString(2, s2);
            st.setString(3, s3);
            st.executeUpdate();
            
            st.close();
            con.close();
    }}

 /*   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
