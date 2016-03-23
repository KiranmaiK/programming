package projects.kiran.programming.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projects.kiran.programming.myapp.form.UserForm;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
	UserForm userForm = new UserForm();
	model.addAttribute("loginForm", userForm);
	return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
	return "main";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@ModelAttribute("loginForm") UserForm userForm, ModelMap model) {
	if (userForm != null) {
	    if ("1".equals(userForm.getPassword())) {
		//HttpSession session = request.getSession();
		//session.setAttribute("username", username);

		return "main";
	    } else {
		model.addAttribute("isLoginFailure", "true");
		return "login";
	    }
	}
	model.addAttribute("isLoginFailure", "true");
	return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
	return login(model);
    }

}
