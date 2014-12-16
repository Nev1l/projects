package by.epam.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping(value = "/login.do")
	public String home(Locale locale, Model model) {
		model.addAttribute("info", "GG WP");
		return "login";
	}
}
