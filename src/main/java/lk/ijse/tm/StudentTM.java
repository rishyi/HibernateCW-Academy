package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {

    private String studentId;
    private String firstname;
    private String lastname;
    private String Address;
    private String contact;
}
