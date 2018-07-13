package in.net.dpl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import in.net.dpl.model.Tariff;
//import in.net.dpl.model.TenderFileRowMapper;
import in.net.dpl.model.EnquiryModel; 

public class TariffDAO {

	private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}  
	
	public int saveTariff(String title,String description,String sr_no,String viewFlag){  
	    
		System.out.println("Sr No-"+sr_no);
		String query="insert into tariff_upload_master(title,description,sr_no,view_flag,upload_date) "
	    		+ "values('"+title+"','"+description+"','"+sr_no+"','"+viewFlag+"',NOW())";  
	    return jdbcTemplate.update(query);  
	}  
	
	public int saveTariffFile(String sr_no,String fileName){  
	    String query="insert into tariff_upload_index(sr_no,file_name) "
	    		+ "values('"+sr_no+"','"+fileName+"')";  
	    return jdbcTemplate.update(query);  
	}
	
	
	public List<String> findTariffFile(String params){  
		String sql = "SELECT file_name FROM tariff_upload_index WHERE sr_no='"+params+"'";
		List<String> tms = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			
			                 tms.add(String.valueOf(row.get("file_name")));
			
			        }			
		return tms;
	}
	
	public String tariffLoginAuth(String user_id,String password){  
		String sql = "SELECT a.user_id AS user_id FROM tariff_user a WHERE a.user_id='"+user_id+"' AND a.password='"+password+"' ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		int counter=0;
		for (Map row : rows) {
						
			            counter++;
			}
			if(counter>0){
				return "AUTHORIZED";
			}
			 			
			else return "UNAUTHORIZED";
		
	}
	
	public List<Tariff> findTariff(){  
		String sql = "select title,description,sr_no,view_flag from tariff_upload_master order by upload_date desc";
		List<Tariff> listTariff = new ArrayList<Tariff>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			 
							Tariff tariff=new Tariff();
							tariff.setTitle(String.valueOf(row.get("title")));
							tariff.setDescription(String.valueOf(row.get("description")));
							tariff.setSr_no(String.valueOf(row.get("sr_no")));
							tariff.setView_flag(String.valueOf(row.get("view_flag")));
							listTariff.add(tariff);
			
			        }	
		
		System.out.println("Tariff-"+listTariff.size());
		return listTariff;
	}
	
	public List<Tariff> findSelectedTariff(String srNo){  
		String sql = "select title,description,sr_no,view_flag from tariff_upload_master where sr_no='"+srNo+"'";
		List<Tariff> listTariff = new ArrayList<Tariff>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			 
							Tariff tariff=new Tariff();
							tariff.setTitle(String.valueOf(row.get("title")));
							tariff.setDescription(String.valueOf(row.get("description")));
							tariff.setSr_no(String.valueOf(row.get("sr_no")));
							tariff.setView_flag(String.valueOf(row.get("view_flag")));
							listTariff.add(tariff);
			
			        }	
		
		System.out.println("Tariff-"+listTariff.size());
		return listTariff;
	}
	
	
public void changeTariff(String sr_no,String viewFlag){  
	    
		System.out.println("Sr No-"+sr_no);
		String query="update tariff_upload_master set view_flag='"+viewFlag.toUpperCase()+"' where sr_no='"+sr_no+"' ";  
	    jdbcTemplate.update(query);  
	} 
	
}