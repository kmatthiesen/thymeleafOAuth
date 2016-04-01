package catalyst.thymeleaf.controller;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import catalyst.thymeleaf.entity.User;
import catalyst.thymeleaf.service.UserService;

@Controller
public class HomeController {
	
	private ModelAndView mav;
	
	@Autowired
	private UserService service;
	
	/**
	 * @param service the service to set
	 */
	public void setService(UserService service) {
	
		this.service = service;
	}

	private ModelAndView checkPrincipal(ModelAndView mav, Principal principal) {
		
		if (principal == null) {
			
			mav.addObject("user", null);
		}
		else {
			
			mav.addObject("user", service.getUser(principal.getName()));
		}
	
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		mav = new ModelAndView("index");
		
		mav = checkPrincipal(mav, principal);
		
		return mav;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		mav = new ModelAndView("welcome");
		
		mav.addObject("user", new User());
		
		return mav;
	}
	
	@RequestMapping(value = "/pplList", method = RequestMethod.GET)
	public ModelAndView pplList(Principal principal) {
		mav = new ModelAndView("pplList");
		
		mav = checkPrincipal(mav, principal);
		
		mav.addObject("pplList", service.getPplList());
		return mav;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(Principal principal) {
		mav = new ModelAndView("profile");
		
		mav = checkPrincipal(mav, principal);
	
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		
		return "login";
	}
	
	@RequestMapping("/login-fail")
	public String loginError(Model model) {

		model.addAttribute("loginError", true);
		return "login";
	}

}
