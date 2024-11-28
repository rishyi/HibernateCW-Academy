package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getFirstname(), studentDTO.getLastname(), studentDTO.getAddress(), studentDTO.getContact()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getFirstname(),studentDTO.getLastname(),studentDTO.getAddress(),studentDTO.getContact()));
    }

    @Override
    public ArrayList<StudentDTO> loadAllStudents() throws IOException {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();
        for(Student s: all){
            allStudents.add(new StudentDTO(s.getStudentId(),s.getFirstname(),s.getLastname(),s.getAddress(),s.getContact()));
        }
        return allStudents;
    }

    @Override
    public boolean deleteStudent(String studentId) throws IOException {
        return studentDAO.delete(studentId);
    }

    @Override
    public StudentDTO searchById(String id) throws IOException {
            Student student=   studentDAO.search(id);
        return new StudentDTO(student.getStudentId(),student.getFirstname(),student.getLastname(),student.getAddress(),student.getContact());

    }
    @Override
    public String getStudentName(String studentId) throws IOException {
        return studentDAO.getStudentName(studentId);
    }

}
