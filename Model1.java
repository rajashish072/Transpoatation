package Transpotation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Model1")
public class Model1 extends HttpServlet {

	Connection con;
	public void init() throws ServletException{
		  try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/spring","root","Ashish@6060");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
      String btn1=req.getParameter("btn");
	  String month1=(String) req.getAttribute("month");
         if(btn1.equals("insert"))
         {
		String query="select * from transpotation";
		try {
			ResultSet rs=null; 
			java.sql.Statement stmt=null;
		
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{ 
				   String month=rs.getString(2);
				   double Balance=rs.getDouble(3);
				   Calculation c1=new Calculation();
				   
				  
				   
				   if(month.equals(month1))
				   {
					   double charge= c1.charge(month, Balance);
					   double gst1=  c1.gst(month, Balance);
					   double netprofit=gst1-charge;
					    String query1="update transpotprofit set transportcharge2%='"+charge+"',transportgst15%='"
					                   +gst1+"',netprofitmonthely='"+netprofit+"' where month='"+month+"'";
						stmt=con.createStatement();
						stmt.executeUpdate(query1);
						PrintWriter pw=resp.getWriter();
						RequestDispatcher rd1=req.getRequestDispatcher("/transpotation.html");
						resp.getWriter().println("<h1>AAAG LGA DO</h1>");
						rd1.include(req, resp);
						
				   }else {
					   double charge= c1.charge(month, Balance);
					   double gst1=  c1.gst(month, Balance);
					   double netprofit=gst1-charge;
				       PreparedStatement pstmt=null;
					String query2="insert into transportprofit values(0,?,?,?)";
					pstmt=con.prepareStatement(query2);
					pstmt.setDouble(1,charge);
					pstmt.setDouble(2,gst1);
					pstmt.setDouble(3,netprofit);
				    int count=pstmt.executeUpdate();
				    PrintWriter pw=resp.getWriter();
					RequestDispatcher rd1=req.getRequestDispatcher("/transpotation.html");
					resp.getWriter().println("<h1></h1>");
					rd1.include(req, resp);
					
			}
		}
	  
		}  
	
	catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	}
	}

}
