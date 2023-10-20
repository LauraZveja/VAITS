package lv.vaits.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class IntController {
	
	@GetMapping("/int")
	public String internat(@RequestParam(name = "localeData") String localeData ){
	 return "lang-page";
	}
	

}
