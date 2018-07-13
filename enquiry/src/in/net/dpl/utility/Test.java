package in.net.dpl.utility;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   System.out.println("Today-"+new TodayAsString().todayWithTimeAsString());
   
   new SendMail().send("saptarshi.edp@gmail.com","1485848195236");
   
	}

}
