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

@WebServlet("/Model2")
public class Model2 extends HttpServlet{
	
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
		
		
		String btn2=req.getParameter("btn1");
		
		
			String q="select * from transportprofit";
			
			try {
				ResultSet rs=null; 
				java.sql.Statement stmt=null;
			
				stmt=con.createStatement();
				rs=stmt.executeQuery(q);
				
				double yearlyProfit=0;
				double YearlyTranspotationCharge=0;
				double minimumnetprofit=0;
				while(rs.next())
				{ 
					  
					   double monthelyprofit=rs.getDouble(4);
					   double transpotationCharge=rs.getDouble(2);
					  

						if(btn2.equals("YNP"))
						{
					       yearlyProfit=yearlyProfit + monthelyprofit;
						}else if(btn2.equals("TC"))
						{
							YearlyTranspotationCharge=YearlyTranspotationCharge + transpotationCharge;
						}else
						{
							String min="select MIN(netprofitmonthely) from transportprofit ";
							stmt=con.createStatement();
							rs=stmt.executeQuery(min);
							  double minnetprofit=0;
							while(rs.next())
							{
								  minnetprofit=rs.getDouble(1);
								 
							}
							 minimumnetprofit=minnetprofit;
							
						}
		         }
				PrintWriter pw=resp.getWriter();
				RequestDispatcher rd1=req.getRequestDispatcher("/transpotation.html");
				resp.getWriter().println("<h1>YEARLY NET PROFIT="+ yearlyProfit+"</h1>");
				resp.getWriter().println("<h1>YEARLY TRANSPOTATION CHARGE="+ YearlyTranspotationCharge+"</h1>");
				resp.getWriter().println("<h1>MINIMUM NET PROFIT IN MONTH="+ minimumnetprofit+"</h1>");
				rd1.include(req, resp);
			}
		  
	         
		
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		}
			
			
			
			
			
		}
		
		
		
	


