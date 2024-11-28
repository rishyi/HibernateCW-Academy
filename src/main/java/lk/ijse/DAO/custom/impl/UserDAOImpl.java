package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.custom.UserDAo;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAo {

    @Override
    public boolean save(User user) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User where userId = :u_id";
        Query query = session.createQuery(hql);
        query.setParameter("u_id", id);

        List<User> userlist = query.list();

        User userL = null;
        for (User st : userlist) {
            userL = new User(st.getUserId(), st.getUserName(), st.getUserRole());
//            System.out.println(userL + "aawaa");
        }

        transaction.commit();
        session.close();

        return userL;
    }


    @Override
    public String getUser(String userId) throws Exception {
        /*try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String sql = "SELECT CAST(password AS CHAR) FROM User WHERE userId = :userId";
            //SELECT CAST(password AS CHAR) FROM User WHERE userId = :userId
            //SELECT CAST(password AS CHAR) FROM Admin WHERE userName = :userName
            return session.createQuery(sql, User.class)
                    .setParameter("userID", userId)
                    .uniqueResult();
        } catch (Exception e) {
            throw new Exception("Error fetching user: " + e.getMessage());
        }*/
        System.out.println("aawaaaa " + userId);
        String password = null;

        // Obtain Hibernate session
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Native SQL query to fetch userId based on userName
            String sql = "SELECT CAST(password AS CHAR) FROM User WHERE userId = :userId";
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("userId", userId);

            // Retrieve the result as a String
            password = (String) query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
      System.out.println(password+"awaaaaaaaaaaaaaaaaaaaaaa");
        return password;

    }

    @Override
    public ArrayList<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        List<Student> list = session.createQuery("from Student ", Student.class).list();
        String code = "cordinator";
        String hql=("from User where userRole = :user_role");
        Query query = session.createQuery(hql);
        query.setParameter("user_role",code);

        ArrayList<User> userList = (ArrayList<User>) query.list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public boolean deleteCordinator(String text) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        String role= "cordinator";
        String hql= ("delete from User where userId = :uID");
        Query query = session.createQuery(hql);
        query.setParameter("uID",text);

        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean changePassword(String hashedPassword,String userId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql =("update User set password= :pw where userId= :u_id");
        Query query = session.createQuery(hql);
        query.setParameter("u_id",userId);
        query.setParameter("pw",hashedPassword);
        query.executeUpdate();
        transaction.commit();
        session.close();
//        System.out.println(hashedPassword+ "hellllllllllllll");
//        System.out.println(userId+ "hiiiiiiiiiiiii");
    return true;
    }

}







