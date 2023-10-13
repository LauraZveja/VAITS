package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Notification;

public interface INotificationRepo extends CrudRepository<Notification, Long>{


}
