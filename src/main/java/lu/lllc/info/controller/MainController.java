package lu.lllc.info.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.addCookie(new Cookie("name", "John"));
		
		model.addAttribute("localName", request.getLocalName());
		model.addAttribute("remoteAddr", request.getRemoteAddr());
		model.addAttribute("remoteHost", request.getRemoteHost());
		model.addAttribute("requestURI", request.getRequestURI());
		model.addAttribute("cookies", request.getCookies());
		model.addAttribute("headerNames", request.getHeaderNames());
		
		return "index";
	}
}
