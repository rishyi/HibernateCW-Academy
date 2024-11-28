package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws IOException;

    UserDTO searchById(String id) throws IOException;




    String getUser(String userName) throws Exception;


    ArrayList<UserDTO> loadAllCordinators() throws IOException;

    boolean deleteCordinator(String text) throws IOException;

    boolean changePassword(String newPassword,String userId) throws IOException;
}
