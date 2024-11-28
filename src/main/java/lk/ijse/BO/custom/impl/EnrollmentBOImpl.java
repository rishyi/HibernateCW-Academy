package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.CourseDAO;
import lk.ijse.DAO.custom.EnrollmentDAO;
import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.dto.EnrollmentDTO;
import lk.ijse.entity.Course;
import lk.ijse.entity.Enrollment;
import lk.ijse.entity.Student;

import java.io.IOException;

public class EnrollmentBOImpl implements EnrollmentBO {

    EnrollmentDAO enrollmentDAO = (EnrollmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENROLLMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);



    @Override
    public String getCurrentId() throws IOException {
        return enrollmentDAO.getNextOrderId();
    }

    @Override
    public String generateNewRegisterId() {
        return enrollmentDAO.getCurrentId();


    }

    @Override
    public boolean registerStudent(EnrollmentDTO enrollmentDTO) throws IOException {
        Student student = studentDAO.findById(enrollmentDTO.getStudentId());
        Course course = courseDAO.findById(enrollmentDTO.getCourseId());

        return enrollmentDAO.register(new Enrollment(enrollmentDTO.getRegistrationId(),enrollmentDTO.getRegistrationDate(),enrollmentDTO.getDownPayment(),enrollmentDTO.getBalance(),student,course));
    }


}


