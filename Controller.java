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

@WebServlet("/Transport")
public class Controller extends HttpServlet {
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
		
		String month=req.getParameter("month");
		String Ammount=req.getParameter("ammount");
		
		//Parsing
		double ammount=Double.parseDouble(Ammount);
		
		
		
		
		
		
		
		String query="select * from transpotation where month='"+month+"'  ";
		try {
			ResultSet rs=null; 
			java.sql.Statement stmt=null;
		
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			String month1=null;
			double Balance=0;
			while(rs.next())
			{      
				   
				    month1=rs.getString(2);
				    Balance=rs.getDouble(3);
				   
				  
			}
			 if(month.equals(month1))
			   {
				    String query1="update transpotation set ammount='"+ammount+"' where month='"+month+"'";
					stmt=con.createStatement();
					stmt.executeUpdate(query1);
					    PrintWriter pw=resp.getWriter();
						RequestDispatcher rd1=req.getRequestDispatcher("/transpotation.html");
						resp.getWriter().println("<h1>"+month1+" Month Ammount Updated Successfully</h1>");
						rd1.include(req, resp);
					
			   }else {
				    
				      PreparedStatement pstmt=null;
					  String query3="insert into transpotation values(0,?,?)";
                    pstmt=con.prepareStatement(query3);
			          pstmt.setString(1,month);
					  pstmt.setDouble(2,ammount);
					  int count=pstmt.executeUpdate();
					     PrintWriter pw=resp.getWriter();
						RequestDispatcher rd1=req.getRequestDispatcher("/transpotation.html");
						resp.getWriter().println("<h1>"+month+" <i>Month and Ammount Inserted Successfully<i></h1>");
						rd1.include(req, resp);
						
                   }
			
//			      req.setAttribute("month",month);
//			      RequestDispatcher rd1=req.getRequestDispatcher("/Model1");
//				  rd1.include(req, resp);
			
			
			
		}
		
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	  
         
		
		
		
		
		

				   
				  

				  
				
		   
		
	       
		    
		   
		   
	
		
		
		  
		
	}

}
