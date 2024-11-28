package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String studentId;

    private String firstname;
    private String lastname;
    private String address;
    private String contact;


    public Student(String studentId,String firstname,String lastname, String address, String contact) {
        this.studentId=studentId;
        this.firstname = firstname;
        this.lastname =lastname;
        this.address = address;
        this.contact=contact;
    }



    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Enrollment> enrollmentList=new ArrayList<>();

}
