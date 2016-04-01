package catalyst.thymeleaf.webservice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalyst.thymeleaf.entity.User;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kmatthiesen on 3/22/2016.
 */
@RestController
public class webservice {

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void getUser(@ModelAttribute(value="user") User user) {
		System.out.println(user.toString());
	}
}
