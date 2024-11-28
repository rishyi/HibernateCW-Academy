package lk.ijse.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class EnrollmentTM {
    private String registrationId;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private double courseFEE;
    private  String registrationDate;
    private JFXButton btnRemove;


}
