package lv.vaits.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Course;
import lv.vaits.models.Notification;
import lv.vaits.repos.INotificationRepo;
import lv.vaits.services.INotificationServices;

@Service
public class NotificationServicesImplementation implements INotificationServices{
	
	@Autowired
	private INotificationRepo notificationRepo;

	@Override
	public ArrayList<Notification> retrieveAllNotifications() {
	  return (ArrayList<Notification>) notificationRepo.findAll();
	}
	
}
