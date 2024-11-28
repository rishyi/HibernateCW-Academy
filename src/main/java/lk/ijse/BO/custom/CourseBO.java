package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.CourseDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface CourseBO extends SuperBO {


    boolean saveCourse(CourseDTO courseDTO) throws IOException;

    boolean deleteCourse(String id) throws IOException;

    CourseDTO searchCourse(String id) throws IOException;

    boolean updateCourse(CourseDTO courseDTO) throws IOException;

    ArrayList<CourseDTO> getAllCourses() throws IOException;

    ArrayList<String> getCourseIds() throws IOException;
}
