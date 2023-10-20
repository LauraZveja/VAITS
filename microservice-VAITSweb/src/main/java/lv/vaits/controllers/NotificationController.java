package lv.vaits.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lv.vaits.services.INotificationServices;

@Controller
public class NotificationController {
	
	@Autowired
	private INotificationServices notificationServices;
	
	 @Autowired
	    private LocaleResolver localeResolver;

	
    // localhost:8081/notifications/showAll
    @GetMapping("/notifications/showAll")
    public String allNotificationsFunc(Model model,
        @RequestParam(value = "lang", required = false) String lang,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        if (lang != null) {
            // Set the user's selected locale
            Locale newLocale = new Locale(lang);
            localeResolver.setLocale(request, response, newLocale);
        }
    	
    	model.addAttribute("allNotifications", notificationServices.retrieveAllNotifications());
        return "all-notifications-page" ;
    }

}
