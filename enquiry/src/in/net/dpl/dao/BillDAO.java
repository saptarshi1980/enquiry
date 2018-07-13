package in.net.dpl.dao;

import in.net.dpl.model.PaymentReceipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class BillDAO {
	
private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}

	
	public List<String> billList(String conNo){  
		String sql = "select distinct bill_prd FROM consdb.x_bill where party_code='"+conNo+"' ORDER BY bill_date desc ";
		System.out.println(sql);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<String> lbill=new ArrayList<String>();
		for (Map row : rows) {
						
						lbill.add(String.valueOf(row.get("bill_prd")));
						
						
			}
			
	     return lbill;
}
}
