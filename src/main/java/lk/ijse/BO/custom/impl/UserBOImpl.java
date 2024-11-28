package lk.ijse.BO.custom.impl;

import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.UserDAo;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import lk.ijse.util.PasswordUtils;
import org.mindrot.jbcrypt.BCrypt;

import org.apache.commons.codec.digest.DigestUtils;


import java.io.IOException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAo userDAo=(UserDAo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) throws IOException {
        // Hash the password using BCrypt and generate a salt
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());

        // Replace the plain password with the hashed one
        // Save the user entity with the hashed password
        return userDAo.save(new User(userDTO.getUserId(), hashedPassword, userDTO.getUserName(), userDTO.getUserRole()));
    }

    @Override
    public UserDTO searchById(String id) throws IOException {
        User user = userDAo.search(id);
//        System.out.println("methanata enawa" + user.getUserId());
        return new UserDTO(user.getUserId(),user.getUserName(),user.getUserRole());
    }




    @Override
    public String getUser(String userId) throws Exception {
        String user = userDAo.getUser(userId);

//        if (user == null) {
//            throw new Exception("No user found with userId: " + userId);
//        }

        return user;
    }

    @Override
    public ArrayList<UserDTO> loadAllCordinators() throws IOException {
        ArrayList<UserDTO> allCordinators = new ArrayList<>();
        ArrayList<User> all = userDAo.getAll();
        for(User s: all){
            allCordinators.add(new UserDTO(s.getUserId(),s.getUserName()));
        }
        return allCordinators;
    }

    @Override
    public boolean deleteCordinator(String text) throws IOException {
        return userDAo.deleteCordinator(text);
    }

    @Override
    public boolean changePassword(String newPassword,String userId) throws IOException {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return userDAo.changePassword(hashedPassword,userId);
    }


}

