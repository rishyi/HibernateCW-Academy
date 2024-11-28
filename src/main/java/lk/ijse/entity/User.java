package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class User {
@Id
    private String userId;
    private String password;
    private String userName;
    private String userRole;

    public User(String userId, String userName, String userRole) {
        this.userId=userId;
        this.userName=userName;
        this.userRole=userRole;
    }
}
