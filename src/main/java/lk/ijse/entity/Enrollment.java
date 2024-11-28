package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Enrollment {
    @Id
    private String registrationId;
    private String registrationDate;
    private double downPayment;
    private double balance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "programId")
    private Course course;



}
