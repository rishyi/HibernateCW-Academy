package lk.ijse.DAO;

import lk.ijse.BO.custom.impl.UserBOImpl;
import lk.ijse.DAO.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT,CORDINATOR,COURSE,USER,ENROLLMENT
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case USER:
                return new UserDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case CORDINATOR:
                return new CordinatorDAOImpl();
            case ENROLLMENT:
                return new EnrollmentDAOImpl();


            default:
                return null;
        }
    }
}
