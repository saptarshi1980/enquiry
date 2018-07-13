package in.net.dpl.dao;

import in.net.dpl.model.ConsumptionPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReportDAO {

private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}
	
	public String conPattern(String conNo) 
	{

		StringBuilder str = new StringBuilder();
			
					
			String sql="SELECT DATE_FORMAT(STR_TO_DATE(bill_month,'%Y%m'),'%M-%y') AS bill_month,CAST(unit AS CHAR) AS unit  FROM android.v_last_3_bill  WHERE con_no='"+conNo+"' ORDER BY STR_TO_DATE(bill_month,'%Y%m') DESC LIMIT 5";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			List<String> lbill=new ArrayList<String>();
			for (Map row : rows) {
							
							lbill.add(String.valueOf(row.get("bill_prd")));
							if(str.length()>0)
								str.append("|"+String.valueOf(row.get("bill_month"))+"|"+String.valueOf(row.get("unit")));
								else
								str.append(String.valueOf(row.get("bill_month"))+"|"+String.valueOf(row.get("unit")));	
							
				}
			
			
			
			return str.toString();
			
	}
	
	public String getSD(String refNo){
		
		String sql="select remarks from transaction where transaction_ref_no='"+refNo+"'";
		String remarks=null;
		
		try{
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			
			
			for (Map row : rows) {
				
				
				remarks=String.valueOf(row.get("remarks"));
				
				
				
							
				           
				}
			}catch(DataAccessException ex){
				ex.printStackTrace();
			}
		
		
		String temp=null;
		
		try{
			
			temp=remarks.substring(0,remarks.indexOf("-"));
		}catch(Exception ex){
			temp="0";
		}
		
		
		return temp;
		
		
		
		
	}
	
	
}
