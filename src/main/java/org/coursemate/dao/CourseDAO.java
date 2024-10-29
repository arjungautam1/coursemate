package org.coursemate.dao;

import org.coursemate.model.Course;

import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);
    Course getCourseById(int id);
    List<Course> getAllCourses();
    void updateCourse(Course course);
    void deleteCourse(int id);
}
