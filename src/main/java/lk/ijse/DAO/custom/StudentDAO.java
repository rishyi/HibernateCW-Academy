package lk.ijse.DAO.custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentDAO extends SuperDAO {
    boolean save(Student student) throws IOException;

    boolean update(Student student) throws IOException;

    ArrayList<Student> getAll() throws IOException;

    boolean delete(String studentId) throws IOException;

    Student search(String id) throws IOException;

    String getStudentName(String studentId) throws IOException;

    Student findById(String studentId);
}
