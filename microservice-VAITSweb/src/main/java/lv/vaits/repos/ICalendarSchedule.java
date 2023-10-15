package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.CalendarSchedule;

public interface ICalendarSchedule extends CrudRepository<CalendarSchedule, Long> {

}
