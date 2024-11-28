package lk.ijse.DAO.custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;

public interface UserDAo extends SuperDAO {
    boolean save(User user) throws IOException;

    User search(String id) throws IOException;


    String getUser(String userName) throws Exception;


    ArrayList<User> getAll() throws IOException;

    boolean deleteCordinator(String text) throws IOException;

    boolean changePassword(String hashedPassword,String userId) throws IOException;
}
