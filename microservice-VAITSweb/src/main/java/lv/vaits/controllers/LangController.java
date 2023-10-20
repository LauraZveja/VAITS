package lv.vaits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LangController {
	
	@GetMapping("/international")
    public String getLangPage() {
        return "international";
    }

}
