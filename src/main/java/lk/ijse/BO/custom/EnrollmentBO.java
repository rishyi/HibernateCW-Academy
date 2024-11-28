package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.EnrollmentDTO;

import java.io.IOException;

public interface EnrollmentBO extends SuperBO {
    String getCurrentId() throws IOException;

    String generateNewRegisterId();

    boolean registerStudent(EnrollmentDTO enrollmentDTO) throws IOException;
}
