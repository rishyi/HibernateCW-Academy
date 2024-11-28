package lk.ijse.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class CourseDTO {



    private String programId;

    private String programName;

    private String duration;

    private double fee;

}
