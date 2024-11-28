package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentDTO {

    private String registrationId;
    private String registrationDate;
    private double downPayment;
    private double balance;
    private String studentId;
    private String courseId;
}
