package uz.isystem.studentweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.studentweb.exception.ServerBadRequestException;
import uz.isystem.studentweb.model.Course;
import uz.isystem.studentweb.model.User;
import uz.isystem.studentweb.repository.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course get(Integer id) {
        return getEntity(id);
    }

    public List<Course> getAll() {
        List<Course> courseList = courseRepository.findAllByDeletedAtNull();
        if (courseList.isEmpty()){
            throw new ServerBadRequestException("Course not found!");
        }
        return courseList;
    }

    public boolean create(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        course.setStatus(true);
        courseRepository.save(course);
        return true;
    }

    public boolean update(Integer id, Course course) {
        Course old = getEntity(id);
        course.setId(id);
        course.setCreatedAt(old.getCreatedAt());
        course.setUpdatedAt(LocalDateTime.now());
        course.setStatus(true);
        courseRepository.save(course);
        return true;
    }

    public boolean delete(Integer id) {
        Course course = getEntity(id);
        course.setDeletedAt(LocalDateTime.now());
        courseRepository.save(course);
        return true;
    }

    //Secondary functions
    public Course getEntity(Integer id) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null) {
            throw new ServerBadRequestException("Course not found!");
        }
        return optional.get();
    }
}
