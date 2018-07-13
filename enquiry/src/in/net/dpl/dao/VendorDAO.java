package in.net.dpl.dao;
import in.net.dpl.model.Tariff;
import in.net.dpl.model.EnquiryModel;
import in.net.dpl.model.User;
import in.net.dpl.model.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class VendorDAO {

private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}
	
	public String findVendor(String vendorCode){  
		String sql = "select sr_no,vendor_name,address from vendor where sr_no='"+vendorCode+"' ";
		System.out.println(sql);
		String srNo=null;
		String name=null;
		String address=null;
		int counter=0;

	try{
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	
		for (Map row : rows) {
			

			srNo=String.valueOf(row.get("sr_no"));
			name=String.valueOf(row.get("vendor_name"));
			address=String.valueOf(row.get("address"));

						           
			}
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
		return name+System.lineSeparator()+address;		
	
	}
	
	
	
	public int saveVendor(String vendorCode,String enqCode){  
		String sql = "insert into enquiry_vendor(enq_no,vendor_code) values('"+enqCode+"','"+vendorCode+"') ";
		return jdbcTemplate.update(sql);  		
	
	}
	
	
	public List<String> listVendor(String enq_no){  
		String sql = "select a.vendor_name from vendor a, enquiry_vendor b where b.enq_no='"+enq_no+"' and b.vendor_code=a.sr_no group by vendor_name";
		List<String> tms = new ArrayList<String>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			
			            //String tm = new String();
			            //tm.setFileName(String.valueOf(row.get("file_name")));
						
			            tms.add(String.valueOf(row.get("vendor_name")));
			            
			            
			
			        }
			
			      
			
			        return tms;


		//return getJdbcTemplate().queryForList(sql);
		
	}
	
	
	public List<Vendor> searchVendor(String vendorName){  
		String sql = "select sr_no,vendor_name,address from vendor where vendor_name like '%"+vendorName+"%' ";
		System.out.println(sql);
		
		
		String srNo=null;
		String name=null;
		String address=null;
		int counter=0;
		
		List<Vendor> vendorList=new ArrayList<Vendor>();

	try{
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	
		for (Map row : rows) {
			
			Vendor vendor=new Vendor();
			srNo=String.valueOf(row.get("sr_no"));
			name=String.valueOf(row.get("vendor_name"));
			address=String.valueOf(row.get("address"));
			vendor.setSrNo(srNo);
			vendor.setVendorName(name);
			vendor.setAddress(address);
			
			vendorList.add(vendor);
			System.out.println(srNo);

						           
			}
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
		return vendorList;		
	
	}
	
	public int saveNewVendor(String vendorName,String address,String pin){  
		
		String sql = " select count(*) as rec from vendor ";
		
		
		int counter=0;

	try{
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	
		for (Map row : rows) {
			
			counter=Integer.parseInt(String.valueOf(row.get("rec")));
						           
			}
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
		
		counter++;
		
		
				
		
		
		
		
		
		String sqlInsert = "insert into vendor(sr_no,vendor_name,address,pin) values('"+counter+"','"+vendorName+"','"+address+"','"+pin+"') ";
		jdbcTemplate.update(sqlInsert);
		return counter;
	
	}
	
}
