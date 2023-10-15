package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.vaits.services.INotificationServices;

@Controller
public class NotificationController {
	
	@Autowired
	private INotificationServices notificationServices;
	
    // localhost:8080/notifications/showAll
    @GetMapping("/notifications/showAll")
    public String allNotificationsFunc(Model model) {
    	model.addAttribute("allNotifications", notificationServices.retrieveAllNotifications());
        return "all-notifications-page" ;
    }

}
