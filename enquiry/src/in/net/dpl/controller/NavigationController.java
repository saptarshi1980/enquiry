package in.net.dpl.controller;

import in.net.dpl.dao.BillDAO;
import in.net.dpl.dao.EnquiryDAO;
import in.net.dpl.dao.PaymentRequestDAO;
import in.net.dpl.dao.RcptDAO;
import in.net.dpl.dao.TariffDAO;
import in.net.dpl.dao.UserDAO;
import in.net.dpl.dao.VendorDAO;
import in.net.dpl.model.EnquiryDetails;
import in.net.dpl.model.PaymentReceipt;
import in.net.dpl.model.PaymentRequest;
import in.net.dpl.model.Tariff;
import in.net.dpl.model.EnquiryModel;
import in.net.dpl.model.Unique;
import in.net.dpl.model.User;
import in.net.dpl.model.Vendor;
import in.net.dpl.utility.ChecksumBillDesk;
import in.net.dpl.utility.ConnDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller

public class NavigationController{
	
	
	
	
	
	
	@RequestMapping(value="/index.dpl",method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
 
		
		request.getSession().setAttribute("dept_code", "DCORP");
		ModelAndView model = new ModelAndView("index"); 
		return model;
	}
	
	@RequestMapping(value="/reset.dpl",method = RequestMethod.GET)
	public ModelAndView reset(HttpServletRequest request){
 
		
		request.getSession().setAttribute("dept_code", "DCORP");
		ModelAndView model = new ModelAndView("reset"); 
		return model;
	}
	
	@RequestMapping(value="/selectEnq.dpl",method = RequestMethod.GET)
	public ModelAndView selectEnq(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("selectEnq"); 
		return model;
	}
	
	@RequestMapping(value="/editEnqPage.dpl",method = RequestMethod.GET)
	public ModelAndView editEnqpage(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("selectEnqForEdit"); 
		return model;
	}
	
	
	@RequestMapping(value="/new_enquiry_entry.dpl",method = RequestMethod.GET)
	public ModelAndView newEnquiryEntry(HttpServletRequest request){
 
		//request.getSession().invalidate();
		ModelAndView model = new ModelAndView("new_enq_entry"); 
		return model;
	}
	
	
	@RequestMapping(value="/enquiryEdit.dpl",method = RequestMethod.GET)
	public ModelAndView enquiryEdit(HttpServletRequest request){
 
		//request.getSession().invalidate();
		ModelAndView model = new ModelAndView("selectEnqForEdit"); 
		return model;
	}
	
	@RequestMapping(value="/AddVendorToEnq.dpl",method = RequestMethod.GET)
	public ModelAndView AddVendortoenquiry(HttpServletRequest request){
 
		//request.getSession().invalidate();
		ModelAndView model = new ModelAndView("selectEnqForVendorAdd"); 
		return model;
	}
	
	@RequestMapping(value="/DelVendorFromEnq.dpl",method = RequestMethod.GET)
	public ModelAndView delVendortoenquiry(HttpServletRequest request){
 
		//request.getSession().invalidate();
		ModelAndView model = new ModelAndView("selectEnqForVendorDel"); 
		return model;
	}
	
	
	@RequestMapping(value="/enq_basic.dpl",method = RequestMethod.POST)
	public ModelAndView home(@RequestParam("enq_no") String enq_no,@RequestParam("enq_date") String enq_date,@RequestParam("open_date") String open_date,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
    	String deptCode=request.getSession().getAttribute("dept_code").toString();
    	String enqSrNo=null;
		
		
		try{
			enqSrNo=edao.saveEnquiryMaster(enq_no.toUpperCase(),enq_date,open_date,deptCode);
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
    	EnquiryModel em=new EnquiryModel();
    	em.setEnqRefNo(enqSrNo);
    	em.setEnqDate(enq_date);
    	em.setOpeningDate(open_date);
    	ModelAndView model	 = new ModelAndView("enq_details");
		model.addObject("enq_no",enqSrNo);
		model.addObject("enq_date",enq_date);
		model.addObject("open_date",open_date);
		return model;
	}
	
	
	@RequestMapping(value="/enq_details.dpl",method = RequestMethod.POST)
	public ModelAndView enqDetails(@RequestParam("enq_date") String enq_date,@RequestParam("enq_no") String enq_no,@RequestParam("user_id") String user_id,@RequestParam("description") String description,@RequestParam("unit") String unit,@RequestParam("qty") String qty,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
    	
		
		try{
		edao.saveEnquiryDetails(enq_no.toUpperCase(), description.toUpperCase(), unit, qty);
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
    	EnquiryModel em=new EnquiryModel();
    	ModelAndView model	 = new ModelAndView("enq_details");
		model.addObject("enq_no",enq_no);
		model.addObject("user_id",user_id);
		model.addObject("enq_date",enq_date);
		
		return model;
	}
	
	
	@RequestMapping(value="/addVendor.dpl",method = RequestMethod.GET)
	public ModelAndView addVendor(@RequestParam("enq_no") String enq_no,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
    	System.out.println(enq_no);
		EnquiryModel em=new EnquiryModel();
    	ModelAndView model	 = new ModelAndView("addVendor");
		model.addObject("enq_no",enq_no);
		
		
		return model;
	}
	
	@RequestMapping(value="/SaveVendor.dpl",method = RequestMethod.POST)
	public ModelAndView saveVendor(@RequestParam("enq_no") String enq_no,@RequestParam("vendor_code") String vendor_code,HttpServletRequest request){
 
		String enqNo=enq_no.substring(0,enq_no.indexOf(","));
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ModelAndView model	 = new ModelAndView("addVendor");
		
    	System.out.println(enqNo);
    	VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
    	
		
		try{
		vdao.saveVendor(vendor_code, enqNo);
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
    	
		model.addObject("enq_no",enqNo);
		
		
		return model;
	}
	
	@RequestMapping(value="/SaveVendorExistingEnq.dpl",method = RequestMethod.POST)
	public ModelAndView saveVendorExistingEnq(@RequestParam("enq_no") String enq_no,@RequestParam("vendor_code") String vendor_code,HttpServletRequest request){
 
		System.out.println("Inside save vendor existing Enq");
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		ModelAndView model	 = new ModelAndView("addVendorToExisingEndSuccess");

    	
    	VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
    	
		
		try{
		vdao.saveVendor(vendor_code, enq_no);
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
		
		return model;
		
		
		
		
	}
	
	@RequestMapping(value="/VendorExistingEndDel.dpl",method = RequestMethod.POST)
	public ModelAndView delVendorExistingEnq(@RequestParam("enq_no") String enq_no,@RequestParam("vendor_code") String vendor_code,HttpServletRequest request){
 
		
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		ModelAndView model	 = new ModelAndView("addVendorToExisingEndSuccess");

    	
    	VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
    	
		
		try{
		vdao.delVendor(vendor_code, enq_no);
		}catch(DataAccessException ex){
			ex.printStackTrace();
		}
		
		
		return model;
		
		
		
		
	}
	
	@RequestMapping(value="/FindVendor.dpl",method = RequestMethod.GET)
	public void FindVendor(@RequestParam("vendor_code") String vendor_code,HttpServletRequest request,HttpServletResponse response){
 
		System.out.println("Vendor code="+vendor_code);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
    	String vendor=vdao.findVendor(vendor_code);
    	PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(vendor);
		
		
	}
	
	
	@RequestMapping(value="/PrintEnqOC.dpl",method = RequestMethod.GET)
	public void printEnqOC(@RequestParam("enq_no") String enq_no,HttpServletRequest request,HttpServletResponse response){
 
		
		
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
		ConnDB conndb=(ConnDB) ctx.getBean("conn");
    	List<String> vendor=vdao.listVendor(enq_no);
    	
    	String VendorString="";
    	for (String s : vendor)
    	{
    	   VendorString += " < "+s + " > ";
    	}
    	
    	
		System.out.println(VendorString);
		try{
			InputStream jasperStream = this.getClass().getResourceAsStream("/enquiry_issue_oc.jasper");
		    Map<String,Object> params = new HashMap<>();
		    params.put("enq_no", enq_no);
		    params.put("vendor_name", VendorString);
		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conndb.make_connection());

		    
			response.setContentType("application/x-pdf");
		    //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
		    response.setHeader("Content-Disposition", "attachment; filename="+"enquiry_oc"+".pdf");

		    final OutputStream outStream = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
			
			
			
		}catch(JRException ex){
			
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	
	@RequestMapping(value="/PrintEnq.dpl",method = RequestMethod.GET)
	public void printEnq(@RequestParam("enq_no") String enq_no,HttpServletRequest request,HttpServletResponse response){
 
		
		
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
		ConnDB conndb=(ConnDB) ctx.getBean("conn");
    	
		try{
			InputStream jasperStream = this.getClass().getResourceAsStream("/enquiry_issue.jasper");
		    Map<String,Object> params = new HashMap<>();
		    params.put("enq_no", enq_no);
		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conndb.make_connection());

		    
			response.setContentType("application/x-pdf");
		    //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
		    response.setHeader("Content-Disposition", "attachment; filename="+"enquiry"+".pdf");

		    final OutputStream outStream = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
			
			
			
		}catch(JRException ex){
			
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	
	@RequestMapping(value="/listEnq.dpl",method = RequestMethod.POST)
	public ModelAndView billPeriod(@RequestParam("enq_date") String enq_date,HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		ltm=edao.enquiryList(enq_date, deptCode);
		System.out.println(ltm.size());
		
		
		ModelAndView model = new ModelAndView("enqDisplay"); 
		model.addObject("enq", ltm);
		return model;
	}
	
	@RequestMapping(value="/listAllEnq.dpl",method = RequestMethod.GET)
	public ModelAndView listAllEnq(HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		ltm=edao.enquiryListAll(deptCode);
		System.out.println(ltm.size());
		
		
		ModelAndView model = new ModelAndView("EnqListAll"); 
		model.addObject("enq", ltm);
		return model;
	}
	
	
	@RequestMapping(value="/passwordReset.dpl",method = RequestMethod.GET)
	public ModelAndView passwordReset(@RequestParam("username") String user_id,@RequestParam("password")String password,@RequestParam("newpassword") String newpassword,HttpServletRequest request){
	
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserDAO userdao=(UserDAO) ctx.getBean("udao");
		int counter=userdao.checkPresentPassword(user_id, password);
		ModelAndView model=null;
		
		if(counter==0){
			 
			model = new ModelAndView("invalidpassword"); 
		}
		
		else{
			userdao.resetPassword(user_id, newpassword);
			model = new ModelAndView("passwordsuccess");
		}
		
		request.getSession().invalidate();
		return model;
	}
	


	@RequestMapping(value="/SearchVendorPage.dpl",method = RequestMethod.GET)
	public ModelAndView searchVendorPage(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("searchVendor"); 
		return model;
	}
	
	@RequestMapping(value="/AddVendorPage.dpl",method = RequestMethod.GET)
	public ModelAndView addVendorPage(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("newVendor"); 
		return model;
	}
	
	
	@RequestMapping(value="/SearchVendor.dpl",method = RequestMethod.POST)
	public ModelAndView  searchVendor(@RequestParam("vendor_name") String vendorName,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
		List<Vendor> vl=vdao.searchVendor(vendorName);
		
		for(Vendor v:vl){
			System.out.println(v.getVendorName());
		}
		
		ModelAndView model = new ModelAndView("vendorResult");
		model.addObject("vlist",vl);
		return model;
		
		
	}
	
	
	@RequestMapping(value="/AddNewVendor.dpl",method = RequestMethod.POST)
	public ModelAndView  saveNewVendor(@RequestParam("vendor_name") String vendorName,@RequestParam("address") String address,@RequestParam("pin") String pin,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		VendorDAO vdao=(VendorDAO) ctx.getBean("vdao");
		
		int code=vdao.saveNewVendor(vendorName, address, pin);
		
		ModelAndView model = new ModelAndView("vendorAddResult");
		model.addObject("code",code);
		model.addObject("name",vendorName);
		
		return model;
		
		
	}
	
	@RequestMapping(value="/listEnqEdit.dpl",method = RequestMethod.POST)
	public ModelAndView listEnquiry(@RequestParam("enq_no") String enq_no,HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		ltm=edao.enquiryListByNumber(enq_no, deptCode);
		System.out.println(ltm.size());
		
		
		ModelAndView model = new ModelAndView("enqDisplayEdit"); 
		model.addObject("enq", ltm);
		return model;
	}
	
	@RequestMapping(value="/listEnqVendorAdd.dpl",method = RequestMethod.POST)
	public ModelAndView listEnquiryVendorAdd(@RequestParam("enq_no") String enq_no,HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<Vendor> ltm=new ArrayList<Vendor>();
		ltm=edao.enquiryListVendor(enq_no, deptCode);
		System.out.println(ltm.size());		
		
		ModelAndView model = new ModelAndView("enqDisplayVendorAdd"); 
		model.addObject("enq", ltm);
		model.addObject("no", enq_no);
		
		return model;
	}
	
	
	@RequestMapping(value="/EditEnq.dpl",method = RequestMethod.POST)
	public ModelAndView enquiryEdit(@RequestParam("sr_no") String sr_no,String desc,String qty,String unit,@RequestParam("enq_no") String enq_no,HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		//int index=sr_no.indexOf("-");
		//String enqNo=sr_no.substring(0,index);
		//String srNo=sr_no.substring(index+1,sr_no.length());
		edao.editEnquiryDetails(enq_no, sr_no, desc, unit, qty);
		ModelAndView model = new ModelAndView("selectEnqForEdit"); 
		return model;
	}
	
	
	@RequestMapping(value="/EditEnqPage.dpl",method = RequestMethod.GET)
	public ModelAndView enquiryEditFinal(@RequestParam("sr_no") String sr_no,HttpServletRequest request){
 
		
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<EnquiryModel> ltm=new ArrayList<EnquiryModel>();
		int index=sr_no.indexOf("-");
		String enqNo=sr_no.substring(0,index);
		String srNo=sr_no.substring(index+1,sr_no.length());
		ltm=edao.enquiryByNumber(enqNo, srNo);
		ModelAndView model = new ModelAndView("enqEditFinal"); 
		model.addObject("enq", ltm);
		return model;
	}
	
	
	
	
	@RequestMapping(value="/listEnqVendorDel.dpl",method = RequestMethod.POST)
	public ModelAndView listEnquiryVendorDel(@RequestParam("enq_no") String enq_no,HttpServletRequest request){
 
		String deptCode=String.valueOf(request.getSession().getAttribute("dept_code"));
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EnquiryDAO edao=(EnquiryDAO) ctx.getBean("edao");
		List<Vendor> ltm=new ArrayList<Vendor>();
		ltm=edao.enquiryListVendor(enq_no, deptCode);
		System.out.println(ltm.size());		
		
		ModelAndView model = new ModelAndView("enqDisplayVendorDel"); 
		model.addObject("enq", ltm);
		model.addObject("no", enq_no);
		
		return model;
	}
	/*@RequestMapping(value="/reset.dpl",method = RequestMethod.GET)
	public ModelAndView reset(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("reset"); 
		return model;
	}
	
	@RequestMapping(value="/forgot.dpl",method = RequestMethod.GET)
	public ModelAndView forgot(HttpServletRequest request){
 
		
		ModelAndView model = new ModelAndView("forgot"); 
		return model;
	}
	
	@RequestMapping(value="/home.dpl",method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("con_no") String con_no,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserDAO udao=(UserDAO) ctx.getBean("udao");
    	User user=udao.userLoginAuthWithoutPassword(con_no);
    	ModelAndView model = new ModelAndView("userHome");
		model.addObject("user",user);
		return model;
	}
	
	@RequestMapping(value = "checkUser.dpl", method = RequestMethod.GET)
	public @ResponseBody String findTender(@RequestParam("username") String user_name) {
		
    	System.out.println("Inside Check User");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	UserDAO udao=(UserDAO) ctx.getBean("udao");
    	int count=udao.checkUser(user_name);
    	return String.valueOf(count);
	}
	
	@RequestMapping(value="/finalgateway.dpl",method = RequestMethod.POST)
	public ModelAndView finalgateway(){
 
		ModelAndView model = new ModelAndView("finalgateway"); 
		return model;
	}
	
	@RequestMapping(value="/NewUser.dpl",method = RequestMethod.GET)
	public ModelAndView newUser(){
 
		ModelAndView model = new ModelAndView("userRegistration"); 
		return model;
	}
	
	@RequestMapping(value="/billSelect.dpl",method = RequestMethod.GET)
	public ModelAndView billPeriod(@RequestParam("con_no") String con_no,HttpServletRequest request){
 
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		BillDAO bdao=(BillDAO) ctx.getBean("bdao");
		List<String> billList=bdao.billList(con_no);
		request.setAttribute("billMonth", billList);
		ModelAndView model = new ModelAndView("billPeriodSelector"); 
		return model;
	}
	
	@RequestMapping(value="/billFetch.dpl",method = RequestMethod.GET)
	public ModelAndView billInfoFetch(@RequestParam("con_no") String con_no,HttpServletRequest request){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	PaymentRequestDAO pdao=(PaymentRequestDAO) ctx.getBean("pdao");
		System.out.println(con_no);
		List<PaymentRequest> pr=pdao.billList(con_no);
		
		if(pr.size()>0){
			ModelAndView model = new ModelAndView("paymentPageFirst"); 
			model.addObject("pr",pr);
			System.out.println(pr.size());
			return model;
		}
		else{
			
			UserDAO udao=(UserDAO) ctx.getBean("udao");
	    	User user=udao.userLoginAuthWithoutPassword(con_no);
	    	request.setAttribute("msg", "No Pending bill for payment / Due Date expired");
	    	ModelAndView model = new ModelAndView("userHome");
    		model.addObject("user",user);
    		return model;
		}
	}
	
	@RequestMapping(value="/quickPayHandler.dpl",method = RequestMethod.POST)
	public ModelAndView quickPayHandler(@RequestParam("con_no") String con_no,HttpServletRequest request){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	PaymentRequestDAO pdao=(PaymentRequestDAO) ctx.getBean("pdao");
		System.out.println(con_no);
		List<PaymentRequest> pr=pdao.billList(con_no);
		
		if(pr.size()>0){
			ModelAndView model = new ModelAndView("paymentPageFirst"); 
			model.addObject("pr",pr);
			System.out.println(pr.size());
			return model;
		}
		else{
			
			request.setAttribute("msg", "No Pending bill for payment / Due Date expired");
	    	ModelAndView model = new ModelAndView("noBill");
    		return model;
		}
	}
	
	@RequestMapping(value="/appPayHandler.dpl",method = RequestMethod.GET)
	public ModelAndView appkPayHandler(@RequestParam("con_no") String con_no,HttpServletRequest request){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	PaymentRequestDAO pdao=(PaymentRequestDAO) ctx.getBean("pdao");
		System.out.println(con_no);
		List<PaymentRequest> pr=pdao.billList(con_no);
		
		if(pr.size()>0){
			ModelAndView model = new ModelAndView("paymentPageFirst"); 
			model.addObject("pr",pr);
			System.out.println(pr.size());
			return model;
		}
		else{
			
			request.setAttribute("msg", "No Pending bill for payment / Due Date expired");
	    	ModelAndView model = new ModelAndView("noBill");
    		return model;
		}
	}
	
	@RequestMapping(value="/PayBillHandler.dpl",method = RequestMethod.GET)
	public ModelAndView paybillHandler(@RequestParam("token") String token,HttpServletRequest request){
		
		
		String ChecksumKey ="a3VkNtgWZNwu";
		String[] value_split = token.split("\\|");
		String conNo=value_split[0];
		String billMonth=value_split[1];
		String rebate=value_split[2];
		String amt=value_split[3];
		String sd=value_split[4];
		System.out.println(conNo+"-"+billMonth+"-"+rebate+"-"+amt+"-"+sd);
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	    Unique unique=(Unique) ctx.getBean("unique");
	    String transactionRefNo=String.valueOf(unique.get());
	    String PATTERN="ddMMyy";
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String currDate=dateFormat.format(Calendar.getInstance().getTime());
		String ru="https://thedpl.in/dplcrm/bdresponse.dpl";
		String ip=sd+"-"+rebate;
		//ip=ip+"-"+sd;
		String msg="DURGAPUR"+"|"+transactionRefNo+"|"+"NA"+"|"+amt+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+"INR"+"|"+"NA"+"|"+"R"+"|"+"durgapur"+"|"+"NA"+"|"+"NA"+"|"+"F"+"|"+currDate+"|"+"E"+"|"+conNo+"|"+billMonth+"|"+ip+"|"+"NA"+"|"+"NA"+"|"+ru;
		ChecksumBillDesk cbd=(ChecksumBillDesk)ctx.getBean("checksum");
		String checkSum=cbd.HmacSHA256(msg,ChecksumKey);
		String finalMessage=msg+"|"+checkSum;
		System.out.println("Final Message Parameter which will be sent to BillDesk-"+finalMessage);
		PaymentRequestDAO pdao=(PaymentRequestDAO)ctx.getBean("pdao");
		int countTran=pdao.checkMonth(conNo, billMonth);
		System.out.println("Transaction Counter-"+countTran);
		int transactionInsert=pdao.saveTransaction(conNo, currDate, transactionRefNo, billMonth, amt, ip, msg, checkSum);
		if(countTran==0){
			ModelAndView model = new ModelAndView("finalgateway");
			request.getSession().setAttribute("msg", finalMessage);
			
			return model;
		}
		
		else{
			ModelAndView model = new ModelAndView("warning");
			request.getSession().setAttribute("msg", finalMessage);
			request.setAttribute("warning", "We have detected that you have attempted Similar Transaction earlier. Please ensure that your bank account has NOT been debited.\n\nDouble payments will not be refunded by DPL.");
			return model;
		}
		
		
		
		
		
		
		
	 	
	}
	
	@RequestMapping(value="/RegistrationHandler.dpl",method = RequestMethod.POST)
	public ModelAndView registrationHandler(HttpServletRequest request){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	UserDAO udao=(UserDAO) ctx.getBean("udao");
    	String conNo=request.getParameter("con_no").toString();
    	String userId=request.getParameter("username").toString();
    	String password=request.getParameter("password").toString();
    	String email=request.getParameter("email").toString();
    	String mobile=request.getParameter("phone").toString();
    	
    	
    	System.out.println(conNo+" "+userId+" "+password);
    	
    	
    	int status=udao.saveUser(conNo,userId,password,mobile,email);
    	
    	if(status>0){
    	
    		ModelAndView model = new ModelAndView("userCreated");
    		return model;
    	}
    	
    	else {
    		
    		ModelAndView model = new ModelAndView("error");
    		return model;
    	}
		 
		
	}
	
	
	
	
	@RequestMapping(value="/passwordReset.dpl",method = RequestMethod.GET)
	public ModelAndView passwordReset(@RequestParam("username") String user_id,@RequestParam("password")String password,@RequestParam("newpassword") String newpassword,HttpServletRequest request){
	
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		UserDAO userdao=(UserDAO) ctx.getBean("udao");
		int counter=userdao.checkPresentPassword(user_id, password);
		ModelAndView model=null;
		
		if(counter==0){
			 
			model = new ModelAndView("invalidpassword"); 
		}
		
		else{
			userdao.resetPassword(user_id, newpassword);
			model = new ModelAndView("passwordsuccess");
		}
		
		request.getSession().invalidate();
		return model;
	}
	
	@RequestMapping(value="/conValidation.dpl",method = RequestMethod.GET)
	public ModelAndView conValidation(){
 
		ModelAndView model = new ModelAndView("inputConNumber"); 
		return model;
	}
	
	
	
	
	
	
		
	@RequestMapping(value="/quickpay.dpl",method = RequestMethod.GET)
	public ModelAndView quickPay(){
 
		ModelAndView model = new ModelAndView("quickpay"); 
		return model;
	}
	
	@RequestMapping(value="/quickRcpt.dpl",method = RequestMethod.GET)
	public ModelAndView quicrcpt(){
 
		ModelAndView model = new ModelAndView("quickRcpt"); 
		return model;
	}
	
	
	@RequestMapping(value="/quickRcptHandler.dpl",method = RequestMethod.POST)
	public ModelAndView quickRcptHandler(@RequestParam("con_no") String con_no,HttpServletRequest request){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		//rcptdao
		 RcptDAO payrcptdao =(RcptDAO) ctx.getBean("rcptdao");
		 
		 List<PaymentReceipt> alpr=payrcptdao.rcptList(con_no);
		 request.setAttribute("info", alpr);
		 request.setAttribute("con_no", con_no);
				
		 ModelAndView model = new ModelAndView("downloadRcpt"); 
		 return model;
	}
	
	
*/	
	
	
}
