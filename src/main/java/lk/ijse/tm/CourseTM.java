package lk.ijse.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseTM {

    private String programId;

    private String programName;

    private String duration;

    private double fee;
}
