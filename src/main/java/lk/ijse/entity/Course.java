package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Course {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Id
    private String programId;

    private String programName;

    private String duration;

    private double fee;


    public Course(String programId,String programName,String duration, double fee ) {
        this.programId = programId;
        this.programName = programName;
        this.duration=duration;
        this.fee=fee;
    }

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Enrollment> enrollmentList=new ArrayList<>();

}