package lv.vaits.user.controllers.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilsController {

	@GetMapping("/my-access-denied")
	public String accessDenied() {
		return "access-denied.html";
	}
	
}
