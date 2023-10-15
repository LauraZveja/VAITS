package lv.vaits.services;

import java.util.ArrayList;

import lv.vaits.models.Notification;

public interface INotificationServices {
	
	ArrayList<Notification> retrieveAllNotifications();

}
