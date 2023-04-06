package Transpotation;

public class Calculation {
	
	public double charge(String month,double bal)
	{
		double charge=(bal*2)/100;
		return charge;
		
	}
	public double  gst(String month,double bal)
	{
		double gst=(bal*15)/100;
		return gst;
		
	}
	


}
