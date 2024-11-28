package lk.ijse.BO.custom.impl;

import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.CourseDAO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.entity.Course;

import java.io.IOException;
import java.util.ArrayList;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws IOException {
        return courseDAO.save(new Course(courseDTO.getProgramId(),courseDTO.getProgramName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public boolean deleteCourse(String id) throws IOException {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO searchCourse(String id) throws IOException {
        Course course = courseDAO.search(id);
        return new CourseDTO(course.getProgramId(),course.getProgramName(),course.getDuration(),course.getFee());
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws IOException {
        return courseDAO.update(new Course(courseDTO.getProgramId(),courseDTO.getProgramName(),courseDTO.getDuration(),courseDTO.getFee()));
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws IOException {
        ArrayList<CourseDTO> allCourses = new ArrayList<>();
        ArrayList<Course> all = courseDAO.getAll();
        for(Course c : all){
            allCourses.add(new CourseDTO(c.getProgramId(),c.getProgramName(),c.getDuration(),c.getFee()));
        }
        return allCourses;

    }

    @Override
    public ArrayList<String> getCourseIds() throws IOException {
        ArrayList<String> allIds = new ArrayList<>();
        ArrayList<String>all = courseDAO.getAllIds();
        for(String p: all){
            allIds.add(p);

        }
        return allIds;
    }
}
