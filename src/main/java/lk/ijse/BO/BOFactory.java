package lk.ijse.BO;

import lk.ijse.BO.custom.StudentBO;
import lk.ijse.BO.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
       STUDENT,CORDINATOR,COURSE,USER, ENROLLMENT,PAYMENT
    }
    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CORDINATOR:
                return new CordinatorBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case USER:
                return new UserBOImpl();
            case ENROLLMENT:
                return new EnrollmentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();



            default:
                return null;
        }
    }
}
