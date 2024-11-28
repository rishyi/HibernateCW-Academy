package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String userName;
    private String userRole;

    public UserDTO(String userId, String userName, String userRole) {
        this.userId=userId;
        this.userName=userName;
        this.userRole=userRole;
    }

    public UserDTO(String userId, String userName) {
        this.userId=userId;
        this.userName=userName;
    }
}
