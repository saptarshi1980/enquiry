package in.net.dpl.controller;

import in.net.dpl.dao.PaymentRequestDAO;
import in.net.dpl.dao.RcptDAO;
import in.net.dpl.dao.ReportDAO;
import in.net.dpl.model.*;
import in.net.dpl.utility.ConnDB;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities; /* This class will return the chart to the output stream */
import org.jfree.chart.JFreeChart; /* This class will hold the chart object */
import org.jfree.data.category.DefaultCategoryDataset; /* This class will help the servlet to load chart data */
import org.jfree.chart.plot.PlotOrientation;

	
	@Controller
	
	public class ReportController{
		
		String name,conNo,firstUnit,secondUnit,thirdUnit,firstMonth,secondMonth,thirdMonth,fourthUnit,fifthUnit,fourthMonth,fifthMonth;
		
		
				
		@RequestMapping(value="/PaymentStatus.dpl",method = RequestMethod.GET)
		@ResponseBody
		public void tariffindex(@RequestParam("ref_no") String ref_no,HttpServletResponse response){
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		    ConnDB conndb=(ConnDB) ctx.getBean("conn");
		    System.out.println("inside report controller 08012018");
		    //DataSource ds = (DataSource)ctx.getBean("ds", DataSource.class);
		    
		    ReportDAO rdao=(ReportDAO)ctx.getBean("rptdao");
			String sd=rdao.getSD(ref_no);
			try{
				double i=Double.parseDouble(sd);
				
			}catch(NumberFormatException ex){
				sd="0";
			}
			
			try{
				InputStream jasperStream = this.getClass().getResourceAsStream("/prcpt_bd.jasper");
			    Map<String,Object> params = new HashMap<>();
			    params.put("ref_no", ref_no);
			    params.put("sd", sd);
			    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conndb.make_connection());

			    
				response.setContentType("application/x-pdf");
			    //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
			    response.setHeader("Content-Disposition", "attachment; filename="+"DPLPaymentReceipt"+".pdf");

			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
				
				
				
				
			}catch(JRException ex){
				
				ex.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*ModelAndView model = new ModelAndView("report"); 
			
			return model;*/
		}
		
		
		@RequestMapping(value="/billPrint.dpl",method = RequestMethod.GET)
		@ResponseBody
		public void billPrint(@RequestParam("billmonth") String bill_month,HttpServletResponse response,HttpServletRequest request){
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		    ConnDB conndb=(ConnDB) ctx.getBean("conn");
		    //DataSource ds = (DataSource)ctx.getBean("ds", DataSource.class);
		    String conNo=request.getSession().getAttribute("con_no").toString();
			
			try{
				InputStream jasperStream = this.getClass().getResourceAsStream("/electricbill.jasper");
			    Map<String,Object> params = new HashMap<>();
			    params.put("consumer_no",conNo);
		        params.put("bill_prd",bill_month);
			    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conndb.make_connection());

			    
				response.setContentType("application/x-pdf");
			    //response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
			    response.setHeader("Content-Disposition", "attachment; filename="+"bill"+".pdf");

			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
				
				
				
				
			}catch(JRException ex){
				
				ex.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*ModelAndView model = new ModelAndView("report"); 
			
			return model;*/
		}
		
		
		@RequestMapping(value="/Rcpt.dpl",method = RequestMethod.GET)
		public ModelAndView index(@RequestParam("con_no") String con_no,HttpServletRequest request){
			
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
			//rcptdao
			 RcptDAO payrcptdao =(RcptDAO) ctx.getBean("rcptdao");
			 
			 List<PaymentReceipt> alpr=payrcptdao.rcptList(con_no);
			 request.setAttribute("info", alpr);
			 request.setAttribute("con_no", con_no);
					
			 ModelAndView model = new ModelAndView("downloadRcpt"); 
			 return model;
		}
		
		
		@RequestMapping(value="/graph.dpl",method = RequestMethod.GET)
		@ResponseBody
		public void graph(@RequestParam("con_no") String con_no,HttpServletResponse response,HttpServletRequest request) throws IOException{
			
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		    ReportDAO client=(ReportDAO) ctx.getBean("rptdao");
		    OutputStream out = response.getOutputStream();
		    String data=client.conPattern(con_no);
		    extractString(data);
		    String home="Home";
		    String name=request.getSession().getAttribute("name").toString();
		    
		    		
			try{
				
				DefaultCategoryDataset bar_chart_servlet = new DefaultCategoryDataset();
				bar_chart_servlet.addValue(Integer.valueOf(firstUnit), "Consumer No="+con_no, firstMonth);
				bar_chart_servlet.addValue(Integer.valueOf(secondUnit), "Consumer No="+con_no, secondMonth);
				bar_chart_servlet.addValue(Integer.valueOf(thirdUnit), "Consumer No="+con_no, thirdMonth);
				bar_chart_servlet.addValue(Integer.valueOf(fourthUnit), "Consumer No="+con_no, fourthMonth);
				//bar_chart_servlet.addValue(Integer.valueOf(fifthUnit), "Consumption", fifthMonth);
				JFreeChart BarChartObject=ChartFactory.createBarChart("Consumption Pattern for "+name,"Month","Units Consumed",bar_chart_servlet,PlotOrientation.VERTICAL,true,true,false);
				response.setContentType("image/png"); /* Set the HTTP Response Type */
				ChartUtilities.writeChartAsPNG(out, BarChartObject, 600, 400);
			    
							
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*ModelAndView model = new ModelAndView("report"); 
			
			return model;*/
		
}
		
		public void extractString(String msg){

	        int i=0;
	        String[] value_split = msg.split("\\|");
	        for (String string : value_split) {

	            System.out.println("Response:"+string);

	        }
	        for(i=0;i<value_split.length;i++){

	            if(i==0){
	                this.firstMonth=value_split[i];
	            }
	            else if(i==1){
	                this.firstUnit=value_split[i];
	            }
	            else if(i==2){
	                this.secondMonth=value_split[i];
	            }

	            else if(i==3){
	                this.secondUnit=value_split[i];
	            }
	            else if(i==4){
	                thirdMonth=value_split[i];
	            }
	            else if(i==5){
	                this.thirdUnit=value_split[i];
	            }
	            
	            else if(i==6){
	                fourthMonth=value_split[i];
	            }
	            else if(i==7){
	                this.fourthUnit=value_split[i];
	            }
	            else if(i==8){
	                fifthMonth=value_split[i];
	            }
	            else if(i==9){
	                this.fifthUnit=value_split[i];
	            }

	        }


	        if(this.firstMonth==null || this.firstUnit==null ){
	           this.firstMonth="N/A";
	           this.firstUnit="0";
	        }
	        else if(this.secondMonth==null || this.secondUnit==null ){
	            this.secondMonth="N/A";
	            this.secondUnit="0";
	        }
	        else if(this.thirdMonth==null || this.thirdUnit==null ){
	            this.thirdMonth="N/A";
	            this.thirdUnit="0";
	        }
	        else if(this.fourthMonth==null || this.fourthUnit==null ){
	            this.fourthMonth="N/A";
	            this.fourthUnit="0";
	        }

	        else if(this.fifthMonth==null || this.fifthUnit==null ){
	            this.fifthMonth="N/A";
	            this.fifthUnit="0";
	        }





	    }

		
	}
