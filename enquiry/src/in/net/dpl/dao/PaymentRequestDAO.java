package in.net.dpl.dao;

import in.net.dpl.model.PaymentRequest;
import in.net.dpl.model.EnquiryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class PaymentRequestDAO {

	private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}
	
	
	public List<PaymentRequest> billList(String conNo){  
		String sql = "SELECT a.party_code,DATE_FORMAT(STR_TO_DATE(a.bill_month,'%y%m'),'%M-%Y') AS bill_month_word,a.bill_month,a.bill_amt,a.rebate,DATE_FORMAT(a.due_date,'%d-%m-%Y') AS due_date,b.name,a.sd_bill FROM v_bill_info a,consdb.v_party_info b WHERE a.party_code='"+conNo+"' AND  a.party_code=b.party_code AND a.due_date>=CURDATE() AND a.bill_month NOT IN (SELECT a.bill_month FROM v_bill_info a,transaction b WHERE b.consumer_no='"+conNo+"' AND b.consumer_no=a.party_code AND DATE_FORMAT(STR_TO_DATE(a.bill_month,'%y%m'),'%Y-%m')=DATE_FORMAT(b.bill_month,'%Y-%m') AND b.billdesk_status='0300' ) ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<PaymentRequest> lpr=new ArrayList<PaymentRequest>();
		System.out.println(sql);
		
		int counter=0;
		String deptName=null;
		String userId=null;
		for (Map row : rows) {
						PaymentRequest pr=new PaymentRequest();
						double billAmt=Double.parseDouble(row.get("bill_amt").toString())+Double.parseDouble(row.get("rebate").toString())-Double.parseDouble(row.get("SD_BILL").toString());
						System.out.println("bill Amt-"+billAmt);
						pr.setBillAmount(Double.parseDouble(row.get("bill_amt").toString()));
						pr.setBillMonth(String.valueOf(row.get("bill_month")));
						pr.setConNo(String.valueOf(row.get("party_code")));
						pr.setDueDate(String.valueOf(row.get("due_date")));
						pr.setBillMonthWord((String.valueOf(row.get("bill_month_word"))));
						pr.setConsumer_name((String.valueOf(row.get("name"))));
						pr.setRebate(Double.parseDouble(row.get("rebate").toString()));
						pr.setSdBill(Double.parseDouble(row.get("SD_BILL").toString()));
						pr.setBillValue(billAmt);
						lpr.add(pr);
						
			}
			
	     return lpr;
		
	}
	
	public int checkMonth(String consumerNo,String billMonth){
		
		String query="select * from transaction where consumer_no='"+consumerNo+"' AND bill_month=STR_TO_DATE('"+billMonth+"','%y%m') AND billdesk_status!='0300'";
		int counter=0;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
		for (Map row : rows) {
			
		counter++;	
		}
		
		
		
		return counter;
		
		}
	
public int saveTransaction(String conNo,String tranDate,String tranRefNo,String billMonth,String billAmt,String ip,String original_msg,String checksum){  
	    String query="insert into transaction(consumer_no,transaction_date,transaction_type,transaction_ref_no,bill_month,transaction_amt,remarks,original_status,final_status,billdesk_status,initiation_ts,original_msg,checksum) values('"+conNo+"',str_to_date('"+tranDate+"','%d%m%y'),'E','"+tranRefNo+"',str_to_date('"+billMonth+"','%y%m'),'"+billAmt+"','"+ip+"','PENDING','PENDING','0000',NOW(),'"+original_msg+"','"+checksum+"')";  
	    return jdbcTemplate.update(query);  
	}  

public int timeStampDiff(String refNo){
	
	
	String query="SELECT TIMESTAMPDIFF(MINUTE,initiation_ts,NOW()) AS transaction_time FROM transaction where transaction_ref_no='"+refNo+"'";
	System.out.println("Query=="+query);
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
	String timeStamp=null;
	int transactionTimeInt=0;
	for (Map row : rows) {
		
	timeStamp=String.valueOf((row.get("transaction_time").toString()));
	}
	
	System.out.println("Time Stamp-"+timeStamp);
	try{
		transactionTimeInt=Integer.parseInt(timeStamp);	
	}catch(NumberFormatException ex){
		
		ex.printStackTrace();
	}
	
	
	return transactionTimeInt;
	
	}
public int saveTransaction(String addInfo3,String authStatus,String txnAmt){  
    String query="update transaction set final_status='COMPLETED',billdesk_status='"+authStatus+"' where transaction_ref_no='"+addInfo3+"' and transaction_amt='"+txnAmt+"' and original_status='PENDING'";  
    return jdbcTemplate.update(query);  
} 

public int saveGeneralResponse(String merchantID,String customerID,String txnRefNo,String bankRefNo,String txnAmt,String bankID,String bankMerchantID,String txnType,String currencyName,String itemCode,String securityType,String securityID,String securityPassword,String transactionDate,String authStatus,String settlementType,String addInfo1,String addInfo2,String addInfo3,String addInfo4,String addInfo5,String addInfo6,String addInfo7,String errorStatus,String errorDescription,double rebate,String checksumBD){  
    String query="insert into general_response (merchant_id,consumer_no,bd_trans_ref,bank_ref_no,transaction_amt,bank_id,bank_merchant_id,transaction_type,currency_name,item_code,security_type,security_id,security_password,transaction_date,auth_status,settlement_type,additionalinfo1_date,additionalinfo2_tran_type,additionalinfo3_trans_ref_no,additionalinfo4_bill_month,additionalinfo5_remarks,additionalinfo6,additionalinfo7,error_status,error_description,checksum,completion_ts,rebate) values('"+merchantID+"','"+customerID+"','"+txnRefNo+"','"+bankRefNo+"','"+txnAmt+"','"+bankID+"','"+bankMerchantID+"','"+txnType+"','"+currencyName+"','"+itemCode+"','"+securityType+"','"+securityID+"','"+securityPassword+"',str_to_date('"+transactionDate+"','%d-%m-%Y %H:%i:%s'),'"+authStatus+"','"+settlementType+"','"+addInfo1+"','"+addInfo2+"','"+addInfo3+"','"+addInfo4+"','"+addInfo5+"','"+addInfo6+"','"+addInfo7+"','"+errorStatus+"','"+errorDescription+"','"+checksumBD+"',NOW(),"+rebate+")";  
    return jdbcTemplate.update(query);  
} 



	
	
	

}
