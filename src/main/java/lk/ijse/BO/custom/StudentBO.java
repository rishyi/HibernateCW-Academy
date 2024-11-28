package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO) throws IOException;

    boolean updateStudent(StudentDTO studentDTO) throws IOException;

    ArrayList<StudentDTO> loadAllStudents() throws IOException;

    boolean deleteStudent(String studentId) throws IOException;

    StudentDTO searchById(String id) throws IOException;

    String getStudentName(String studentId) throws IOException;
}
