package in.net.dpl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;


//import in.net.dpl.model.TenderFileRowMapper;
import in.net.dpl.model.EnquiryModel; 

public class EnquiryDAO {

	private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}  
	
	public int saveEnquiryMaster(String enq_no,String enq_date,String open_date,String dept_code){  
	    String query="insert into enq_master(enq_no,enq_date,open_date,dept_code) "
	    		+ "values('"+enq_no+"',str_to_date('"+enq_date+"','%d/%m/%Y'),str_to_date('"+open_date+"','%d/%m/%Y'),'"+dept_code+"')";  
	    return jdbcTemplate.update(query);  
	} 
	public int saveEnquiryDetails(String enq_no,String desc,String unit,String qty){  
	    
		String sr_no=getSrNo(enq_no);
		String query="insert into enq_details(enq_no,sr_no,description,qty,unit) "
	    		+ "values('"+enq_no.toUpperCase()+"','"+sr_no+"','"+desc.toUpperCase()+"','"+qty+"','"+unit.toUpperCase()+"')";  
	    return jdbcTemplate.update(query);  
	}
	
	public List<EnquiryModel> enquiryList(String enq_date,String deptCode){  
		String sql = "SELECT enq_no,date_format(enq_date,'%d-%m-%Y') as enq_date FROM enq_master  where dept_code='"+deptCode+"' and enq_date=str_to_date('"+enq_date+"','%d/%m/%Y') order by enq_date desc ";
		System.out.println(sql);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		
		int counter=0;
		String deptName=null;
		String userId=null;
		for (Map row : rows) {
						EnquiryModel tm=new EnquiryModel();
						tm.setEnqRefNo(String.valueOf(row.get("enq_no")));
						tm.setEnqDate(String.valueOf(row.get("enq_date")));
			            ltm.add(tm);
			}
			
	     return ltm;
		
	}
	
	
	/*public int saveTenderFile(EnquiryModel t,String fileName){  
	    String query="insert into tenderfile(tender_ref_no,tender_upload_date,file_name) "
	    		+ "values('"+t.getTenderRefNo()+"',CURDATE(),'"+fileName+"')";  
	    return jdbcTemplate.update(query);  
	}
	
	public int findTender(EnquiryModel t){  
		String sql = "select * from tendermasternew where tender_ref_no='"+t.getTenderRefNo()+"'";
		 System.out.println("SQL-"+sql);
		//int total = jdbcTemplate.queryForInt(sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
		jdbcTemplate.query(sql, countCallback);
		int rowCount = countCallback.getRowCount();
		System.out.println("Row count-"+rowCount);			
		//return total;
		return rowCount;
	}
	
	public List<String> findTenderFile(String params){  
		String sql = "select file_name from tendermasternew where concat(tender_ref_no,date_format(tender_upload_date,'%d-%m-%y'))='"+params+"'";
		List<String> tms = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			
			            //String tm = new String();
			            //tm.setFileName(String.valueOf(row.get("file_name")));
			            tms.add(String.valueOf(row.get("file_name")));
			
			        }
			
			      
			
			        return tms;


		//return getJdbcTemplate().queryForList(sql);
		
	}
	
	public String tenderLoginAuth(String user_id,String password){  
		String sql = "SELECT a.user_id AS user_id,b.dept_name AS dept_name FROM tender_user a,dept_master b WHERE a.user_id='"+user_id+"' AND a.password='"+password+"' AND a.dept_id=b.dept_id";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		int counter=0;
		String deptName=null;
		String userId=null;
		for (Map row : rows) {
						deptName=String.valueOf(row.get("dept_name"));
			            counter++;
			}
			if(counter>0){
				return deptName;
			}
			      
			
			else return "UNAUTHORIZED";
		
	}
	
	public List<EnquiryModel> tenderList(){  
		String sql = "SELECT tender_group,COUNT(*) AS tender_count FROM tendermasternew GROUP BY tender_group ORDER BY tender_group";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		
		int counter=0;
		String deptName=null;
		String userId=null;
		for (Map row : rows) {
						EnquiryModel tm=new EnquiryModel();
						tm.setTenderGroup(String.valueOf(row.get("tender_group")));
						tm.setCount(String.valueOf(row.get("tender_count")));
			            ltm.add(tm);
			}
			
	     return ltm;
		
	}*/
	
	public String getSrNo(String enqNo){
		
		String srNo=null;
		String sql = "SELECT count(*)+1 as count FROM enq_details WHERE enq_no='"+enqNo+"'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		int counter=0;
		
		for (Map row : rows) {
			
					srNo=String.valueOf(row.get("count"));
					
			}	
		
		
		return srNo;
		
	}
}