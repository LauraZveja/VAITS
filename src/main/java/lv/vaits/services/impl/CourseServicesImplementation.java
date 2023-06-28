package lv.vaits.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Course;
import lv.vaits.repos.ICourseRepo;
import lv.vaits.services.ICourseServices;

@Service
public class CourseServicesImplementation implements ICourseServices {

	@Autowired
	private ICourseRepo courseRepo;

	@Override
	public ArrayList<Course> selectAllCourse() {
		return (ArrayList<Course>) courseRepo.findAll();
	}

}
