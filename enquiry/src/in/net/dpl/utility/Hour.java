package in.net.dpl.utility;

import java.util.Calendar;

public class Hour {

	public static void main(String []args){
		fetchHour();
	}
	
	public static void fetchHour(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(hour);
		if (hour>=0 && hour<=3) {
		   
		}
		else if (hour>=4 && hour<=7){
			
		}
		
		else if (hour>=8 && hour<=11){
			
		}
		
		else if (hour>=12 && hour<=15){
			
		}
		
		else if (hour>=16 && hour<=19){
			
		}
		
		else if (hour>=20 && hour<=23){
			
		}
		
		
		
	}
}
