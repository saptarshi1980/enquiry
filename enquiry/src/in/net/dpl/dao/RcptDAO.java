package in.net.dpl.dao;

import in.net.dpl.model.PaymentReceipt;
import in.net.dpl.model.PaymentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class RcptDAO {

private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}
	
	public List<PaymentReceipt> rcptList(String conNo){  
		String sql = "SELECT consumer_no,date_format(transaction_date,'%d-%m-%y') as transaction_date ,transaction_ref_no,DATE_FORMAT(bill_month,'%M-%y') AS bill_month,transaction_amt FROM transaction where consumer_no='"+conNo+"' AND billdesk_status='0300' ORDER BY transaction_ref_no  DESC LIMIT 10 ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<PaymentReceipt> lpr=new ArrayList<PaymentReceipt>();
		for (Map row : rows) {
						PaymentReceipt rcpt=new PaymentReceipt();
						rcpt.setBillMonth(String.valueOf(row.get("bill_month")));
						rcpt.setConsumerNo(String.valueOf(row.get("consumer_no")));
						rcpt.setTranDate(String.valueOf(row.get("transaction_date")));
						rcpt.setRefNo(String.valueOf(row.get("transaction_ref_no")));
						rcpt.setTranAmt(String.valueOf(row.get("transaction_amt")));
						lpr.add(rcpt);
						
			}
			
	     return lpr;
}
}
