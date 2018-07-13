package in.net.dpl.dao;

import in.net.dpl.model.Tariff;
import in.net.dpl.model.EnquiryModel;
import in.net.dpl.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO {

	
	
private JdbcTemplate jdbcTemplate; 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}
	
	
	/*public String consumerValidation(String consumerNo,String meterNo){  
		String sql = "select distinct a.party_code,b.meter_no,a.name,a.email_id,ifnull(trim(a.mob_no),'') as mob_no FROM consdb.m_party a,consdb.x_bill b where trim(a.party_code)='"+consumerNo.trim()+"' AND trim(b.meter_no)='"+meterNo.trim()+"' and trim(a.party_code)=trim(b.party_code) order by a.party_code ";
		String user=null;
		String mobile=null;
		String email=null;
		int counter=0;
		System.out.println(sql);
		
		try{
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		System.out.println("outsideconsumer validation loop");
		System.out.println("Row Count-"+rows.size());
		
		for (Map row : rows) {
			
			System.out.println("inside consumer validation loop");
			user=String.valueOf(row.get("name"));
			email=String.valueOf(row.get("email_id"));
			mobile=String.valueOf(row.get("mob_no"));

			
			counter++;
			
						
			           
			}
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		if(counter==0){
			return"U";
		}
		else
		return user+"|"+mobile+"|"+email;		
	
	}
	
	
	
	public int saveUser(String con_no,String user_id,String password,String mobile,String email){  
		
		try{
			String query1="INSERT INTO user_master_online(con_no,user_id,pass) VALUES('"+con_no+"','"+user_id+"',AES_ENCRYPT('"+password+"','saptarshi'))";
			String query2="update consdb.m_party set mob_no='"+mobile+"',email_id='"+email+"' where party_code='"+con_no+"'";
			jdbcTemplate.batchUpdate(new String []{query1,query2});
			return 1;
			
		    //return jdbcTemplate.update(query);
		}catch(DataAccessException ex){
			return 0;
		}
	      
	} 
	
public int checkPresentPassword(String user_id,String oldpassword){  
	
	String sql = "SELECT DISTINCT a.party_code,b.meter_no,a.name,a.email_id,a.mob_no FROM consdb.m_party a,consdb.x_bill b,user_master_online c WHERE c.user_id='"+user_id+"' AND AES_DECRYPT(c.pass,'saptarshi')='"+oldpassword+"' AND c.con_no=a.PARTY_CODE AND a.party_code=b.party_code";
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	int counter=0;
	
	for (Map row : rows) {
									
					counter++;
		}	
	
	return counter;
	
}

public int checkUser(String user_id){  
	
	String sql = "SELECT 1 FROM user_master_online WHERE user_id='"+user_id+"'";
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	int counter=0;
	
	for (Map row : rows) {
									
					counter++;
		}	
	
	return counter;
	
}

public int checkConsumer(String con_no){  
	
	String sql = "SELECT 1 FROM user_master_online WHERE con_no='"+con_no+"'";
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	int counter=0;
	
	for (Map row : rows) {
									
					counter++;
		}	
	
	
	return counter;
	
}

public User getUserPass(String con_no,String meter_no){  
	
	User user=new User();
	user.setUserId("U");
	String sql = "SELECT a.user_id AS user_id,AES_DECRYPT(a.pass,'saptarshi') AS pass FROM user_master_online a,consdb.x_bill b WHERE a.con_no='"+con_no+"' AND b.meter_no='"+meter_no+"' AND a.con_no=b.party_code LIMIT 1";
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	int counter=0;
	
	for (Map row : rows) {
		
				user.setUserId(String.valueOf(row.get("user_id")));
				user.setPass(String.valueOf(row.get("pass")));
				counter++;
		}	
	
	
	return user;
	
}
	
public int resetPassword(String user_id,String newpassword){	
		try{
			String query="update user_master_online set pass= AES_ENCRYPT('"+newpassword+"','saptarshi') where user_id='"+user_id+"' ";
		    return jdbcTemplate.update(query);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			
		}
		return 1;
	      
	} */
	
	public User userLoginAuth(String user_id,String password){  
		User user= new User();
		user.setUserName("U");
		String sql = "select * FROM user_master WHERE user_id='"+user_id+"' AND AES_DECRYPT(user_pass,'saptarshi')='"+password+"'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		int counter=0;
		String deptName=null;
		String userId=null;
		for (Map row : rows) {
						
						user.setUserId(String.valueOf(row.get("user_id")));
						user.setUserName(String.valueOf(row.get("user_name")));
						user.setUserDept(String.valueOf(row.get("user_dept")));
						counter++;
			}
			return user;
		
	}
	
	
	public int checkPresentPassword(String user_id,String oldpassword){  
		
		String sql = "SELECT * FROM user_master WHERE user_id='"+user_id+"' AND AES_DECRYPT(user_pass,'saptarshi')='"+oldpassword+"' ";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		int counter=0;
		
		for (Map row : rows) {
										
						counter++;
			}	
		
		return counter;
		
	}
	
	
	/*public User userLoginAuthWithoutPassword(String con_no){  
		User user= new User();
		user.setUserName("U");
		String sql = "SELECT DISTINCT a.party_code,b.meter_no,a.name,a.email_id,a.mob_no FROM consdb.m_party a,consdb.x_bill b,user_master_online c WHERE c.con_no='"+con_no+"' AND c.con_no=a.PARTY_CODE AND a.party_code=b.party_code";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
						
						user.setConNo(String.valueOf(row.get("party_code")));
						user.setMeterNo(String.valueOf(row.get("meter_no")));
						user.setUserName(String.valueOf(row.get("name")));
						user.setEmail(String.valueOf(row.get("email_id")));
						user.setMobile(String.valueOf(row.get("mob_no")));
						user.setUserId(con_no);
						
			}
			return user;
		
	}
	*/
	
	
	
	public int resetPassword(String user_id,String newpassword){	
		try{
			String query="update user_master set user_pass= AES_ENCRYPT('"+newpassword+"','saptarshi') where user_id='"+user_id+"' ";
		    return jdbcTemplate.update(query);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			
		}
		return 1;
	      
	}
}
