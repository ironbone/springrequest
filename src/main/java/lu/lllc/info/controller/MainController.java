package lu.lllc.info.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
//  We can also use the following	
//	@Autowired
//	HttpServletRequest request;
//	
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

  @GetMapping(value = "/sendPDF", produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] sendPDF(HttpServletResponse response) {
		String fileName = "/static/files/example.pdf";
		
		//In the following method the file must be in the classpath
		InputStream in = getClass().getResourceAsStream(fileName);
		
		
		
		try {
			int size = in.available();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=example.pdf");
			response.setContentLength(size);
			return in.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
