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

import in.net.dpl.dao.TenderDAO;
import in.net.dpl.dao.UserDAO;
import in.net.dpl.model.TenderAuth;
import in.net.dpl.model.EnquiryModel;
import in.net.dpl.model.User;
import in.net.dpl.utility.TodayAsString;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
public class UserLoginController {
	
	String user;
	String email;
	String mobile;
	String msg;
	
	/*@RequestMapping(value = "/consumerValidationAuth.dpl", method = RequestMethod.POST)
	public String displayForm(@RequestParam("con_no") String con_no,@RequestParam("meter_no") String meter_no,HttpServletRequest request) {
		System.out.println("Inside controller");
		TenderAuth ta=new TenderAuth();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	UserDAO udao=(UserDAO) ctx.getBean("udao");
    	this.msg=udao.consumerValidation(con_no, meter_no);
    	int conCounter=udao.checkConsumer(con_no);
    	request.getSession().setAttribute("con_no", con_no);
    	extractStringPowerApp(msg);
    	
    	if(msg.matches("U")|| conCounter==1)
    	{
    		//ta.setUserId(user_id);
    		request.setAttribute("duplicate", "User id already exists for given Consumer No. Kindly mail your details to admin@dpl.net.in for reset of password");
    		return "inputConNumber";
    	}else{
    		request.setAttribute("user_name", user);
    		request.setAttribute("email", email);
    		request.setAttribute("mobile", mobile);
    		return "userRegistration";
    	}
	}
	
	
	@RequestMapping(value = "/validateForgot.dpl", method = RequestMethod.POST)
	public String forgot(@RequestParam("con_no") String con_no,@RequestParam("meter_no") String meter_no,HttpServletRequest request) {
		
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	UserDAO udao=(UserDAO) ctx.getBean("udao");
    	User user=udao.getUserPass(con_no,meter_no);
    	if(user.getUserId().matches("U")){
    		request.setAttribute("nouser", "User id already exists for given Consumer No. Kindly mail your details to admin@dpl.net.in for reset of password");
    		return "forgot";
    	}
    	else{
    		
    		request.setAttribute("user_id", user.getUserId());
    		request.setAttribute("pass", user.getPass());
    		return"displayUserPass";
    	}
    	
    	
    	
	}
	*/
	
	
	
	@RequestMapping(value = "/LoginAuth.dpl", method = RequestMethod.POST)
	public ModelAndView loginAuth(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request) {
		System.out.println("Inside controller");
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	UserDAO udao=(UserDAO) ctx.getBean("udao");
    	User user=udao.userLoginAuth(username, password);
    	
    	if(user.getUserName().matches("U")){
    	
    		
    		ModelAndView model = new ModelAndView("index");
    		return model;
    		
    	}
    	
    	else{
    		request.setAttribute("msg", "");
    		request.getSession().setAttribute("user_name", user.getUserName());
    		request.getSession().setAttribute("user_id", user.getUserId());
    		request.getSession().setAttribute("dept_code", user.getUserDept());
    		ModelAndView model = new ModelAndView("userHome");
    		model.addObject("user",user);
    		return model;
    	}
    	
    	
    	
    	
	}
	
	@RequestMapping(value = "/home.dpl", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		
		User user=new User();
		String userId=null;
		String deptCode=null;
		String userName=null;
		
		
		try{
			
			userId=request.getSession().getAttribute("user_id").toString();
			userName=request.getSession().getAttribute("user_name").toString();
			deptCode=request.getSession().getAttribute("dept_code").toString();
		}catch(NullPointerException ex){
			
			ModelAndView model = new ModelAndView("index");
    		return model;
		}
		
		
		if(userId.length()==0){
			ModelAndView model = new ModelAndView("index");
    		return model;
		}

    	
    	else{
    		
    		user.setUserDept(deptCode);
    		user.setUserId(userId);
    		user.setUserName(userName);
    		
    		ModelAndView model = new ModelAndView("userHome");
    		model.addObject("user",user);
    		return model;
    	}
    	
    	
    	
    	
	}
	
public void extractStringPowerApp(String msg){
	    
	    int i=0;
	    
	    int loc=msg.indexOf("|");
	    int loc2=msg.lastIndexOf("|");
	    System.out.println("LOC-"+loc);
	    System.out.println("LOC2-"+loc2);
	    
	    this.user=msg.substring(0,loc);
	    this.mobile=msg.substring(loc+1,loc2);
	    this.email=msg.substring(loc2+1,msg.length());
	    
	    
	    


	}

}
