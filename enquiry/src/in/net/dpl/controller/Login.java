package in.net.dpl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

	
	@Controller
	
	public class Login{
		
		// a comment is added for test in git hub		
		@RequestMapping(value="/tariffUploadLogin.dpl",method = RequestMethod.GET)
		public ModelAndView tariffindex(){
	 
			ModelAndView model = new ModelAndView("tariff_upload_login"); 
			return model;
		}
		
		@RequestMapping(value="/logout.dpl",method = RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest req){
	 
			req.getSession().invalidate();
			ModelAndView model = new ModelAndView("index"); 
			return model;
		}
}
