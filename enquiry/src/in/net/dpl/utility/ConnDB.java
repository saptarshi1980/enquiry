package in.net.dpl.utility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

import javax.servlet.RequestDispatcher;

/**
 *
 * @author Accounts
 */
public class ConnDB {
 
    
public Connection make_connection(){ 
   
  
   Connection con=null;
  String c="jdbc:mysql://172.16.0.53/enquiry";
  
   
   try{

       Class.forName("com.mysql.jdbc.Driver").newInstance();
       con = DriverManager.getConnection(c, "remote", "dgppro1961");
       return con;    
      }catch (SQLException ex) {
       // TODO: handle exception
             ex.printStackTrace();
	} catch (Exception ex) {
	       // TODO: handle exception
        ex.printStackTrace();
} 
	
	return con;
     
    
}

public Connection make_connection_53(){ 
	   
	  
	   Connection con=null;
	   //String c="jdbc:mysql://192.168.0.10/billdesk";
	   String c="jdbc:mysql://172.16.0.53/billdesk";
	   
	   try{

	       Class.forName("com.mysql.jdbc.Driver").newInstance();
	       con = DriverManager.getConnection(c, "remote", "dgppro1961");
	       return con;    
	      }catch (SQLException ex) {
	       // TODO: handle exception
	             ex.printStackTrace();
		} catch (Exception ex) {
		       // TODO: handle exception
	        ex.printStackTrace();
	} 
		
		return con;
	     
	    
	}
}





