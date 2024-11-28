package lk.ijse.DAO.custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.entity.Course;

import java.io.IOException;
import java.util.ArrayList;

public interface CourseDAO extends SuperDAO {
    boolean save(Course course) throws IOException;

    boolean delete(String id) throws IOException;

    Course search(String id) throws IOException;

    boolean update(Course course) throws IOException;

    ArrayList<Course> getAll() throws IOException;

    ArrayList<String> getAllIds() throws IOException;

    Course findById(String courseId);
}
