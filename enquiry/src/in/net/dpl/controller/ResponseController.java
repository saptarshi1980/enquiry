package in.net.dpl.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import in.net.dpl.dao.PaymentRequestDAO;
import in.net.dpl.dao.TenderDAO;
import in.net.dpl.dao.UserDAO;
import in.net.dpl.model.TenderAuth;
import in.net.dpl.model.EnquiryModel;
import in.net.dpl.model.User;
import in.net.dpl.utility.ChecksumBillDesk;
import in.net.dpl.utility.TodayAsString;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
public class ResponseController {
	
	String merchantID=null;
    String customerID=null;
    String txnRefNo=null;
    String bankRefNo=null;
    String txnAmt=null;
    String bankID=null;
    String bankMerchantID=null;
    String txnType=null;
    String currencyName=null;
    String itemCode=null;
    String securityType=null;
    String securityID=null;
    String securityPassword=null;
    String transactionDate=null;
    String authStatus=null;
    String settlementType=null;
    String addInfo1=null;
    String addInfo2=null;
    String addInfo3=null;
    String addInfo4=null;
    String addInfo5=null;
    String addInfo6=null;
    String addInfo7=null;
    String errorStatus=null;
    String errorDescription=null;
    String rebate=null;
	
	String ChecksumKey ="a3VkNtgWZNwu";
	
	@RequestMapping(value = "/bdresponse.dpl", method = RequestMethod.POST)
	public ModelAndView response(@RequestParam("msg") String msg,HttpServletRequest request) {
		System.out.println("********************************************************8");
		String checksumBD=msg.substring(msg.lastIndexOf("|")+1, msg.length());
		String originalMsg=msg.substring(0,msg.lastIndexOf("|"));
		System.out.println("BD Checksum-"+checksumBD);
		
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ChecksumBillDesk cbd=(ChecksumBillDesk)ctx.getBean("checksum");
		String checkSum=cbd.HmacSHA256(originalMsg,ChecksumKey);
		System.out.println("DPL Checksum-"+checkSum);
		extractString(msg);
		int time=getTransactionTime(this.addInfo3);
		ModelAndView model=null;
		if(checkSum.matches(checksumBD)){
			
			if(time<31){
				
				int pos=addInfo5.indexOf("-");
				String ip=addInfo5.substring(0,pos);
				
				
				double rebate=Double.valueOf(addInfo5.substring(pos+1).trim());
				//int posSd=addInfo5.lastIndexOf("-");
				//double rebate=Double.valueOf(addInfo5.substring(pos+1,posSd).trim());
				
				//String sd=addInfo5.substring(posSD+1).trim();
				
				PaymentRequestDAO pdao=(PaymentRequestDAO)ctx.getBean("pdao");
				int updateCount=pdao.saveTransaction(addInfo3,authStatus,txnAmt);
				int updateCountGeneralResponse=pdao.saveGeneralResponse(merchantID,customerID,txnRefNo,bankRefNo,txnAmt,bankID,bankMerchantID,txnType,currencyName,itemCode,securityType,securityID,securityPassword,transactionDate,authStatus,settlementType,addInfo1,addInfo2,addInfo3,addInfo4,addInfo5,addInfo6,addInfo7,errorStatus,errorDescription,rebate,checksumBD);
				request.setAttribute("ref_no", addInfo3);
				request.setAttribute("con_no", customerID);
				request.setAttribute("sd", ip);
				model = new ModelAndView("paymentStatus");
				return model;
									
			}
			else{
				model = new ModelAndView("timeout");
				return model;
			}
			
			
						
			
		}
		
		else{
			
			System.out.println("Check Sum Mismtach");
		}
		return model;
		
	}
	
			
public void extractString(String msg){
	    
	    int i=0;
	    
	    
	    
	    
	    String[] value_split = msg.split("\\|");
	    for (String string : value_split) {

	        
	        System.out.println(string);

	    }
	    for(i=0;i<value_split.length;i++){
	        
	        if(i==0){
	            this.merchantID=value_split[i];
	        }
	        else if(i==1){
	        	 this.addInfo3=value_split[i];
	        }
	        else if(i==2){
	        	 this.txnRefNo=value_split[i];
	        }
	        
	        else if(i==3){
	        	 this.bankRefNo=value_split[i];
	        }
	        else if(i==4){
	        	 this.txnAmt=value_split[i];
	        }
	        else if(i==5){
	        	 this.bankID=value_split[i];
	        }
	        else if(i==6){
	            this.bankMerchantID=value_split[i];
	        }
	        else if(i==7){
	            this.txnType=value_split[i];
	        }
	        else if(i==8){
	            this.currencyName=value_split[i];
	        }
	        else if(i==9){
	            this.itemCode=value_split[i];
	        }
	        else if(i==10){
	            this.securityType=value_split[i];
	        }
	        else if(i==11){
	            this.securityID=value_split[i];
	        }
	        else if(i==12){
	            this.securityPassword=value_split[i];
	        }
	        else if(i==13){
	            this.transactionDate=value_split[i];
	        }
	        else if(i==14){
	            this.authStatus=value_split[i];
	        }
	        else if(i==15){
	            this.settlementType=value_split[i];
	        }
	        else if(i==16){
	            this.addInfo1=value_split[i];
	        }
	        else if(i==17){
	            this.addInfo2=value_split[i];
	        }
	        else if(i==18){
	            this.customerID=value_split[i];
	        }
	        else if(i==19){
	            this.addInfo4=value_split[i];
	        }
	        else if(i==20){
	            this.addInfo5=value_split[i];
	        }
	        else if(i==21){
	            this.addInfo6=value_split[i];
	        }
	        else if(i==22){
	            this.addInfo7=value_split[i];
	        }
	        else if(i==23){
	            this.errorStatus=value_split[i];
	        }
	        else if(i==24){
	            this.errorDescription=value_split[i];
	        }
	        
	    }
	    
	   
	   	       
	}

public int getTransactionTime(String transactionRefNo)
{
	
	int transactionTimeInt=0;
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	PaymentRequestDAO pdao=(PaymentRequestDAO)ctx.getBean("pdao");
	transactionTimeInt=pdao.timeStampDiff(transactionRefNo);
	return transactionTimeInt;
	
}



}
