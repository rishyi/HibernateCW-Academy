package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.custom.EnrollmentDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Enrollment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {





        @Override
        public String getCurrentId() {
            String lastRegisterId = null;

            try (Session session = FactoryConfiguration.getInstance().getSession()) {
                Transaction transaction = session.beginTransaction();

                // Query to get the last registration ID
                String hql = "SELECT registrationId FROM Enrollment ORDER BY registrationId DESC";
                Query<String> query = session.createQuery(hql, String.class);
                query.setMaxResults(1); // Limit the result to one record

                lastRegisterId = query.uniqueResult(); // Get the single result
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return lastRegisterId; // Return the last ID or null if no rows found
        }

    @Override
    public boolean register(Enrollment enrollment) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(enrollment);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
        public String getNextOrderId() {
            String currentId = getCurrentId();

            // If no ID exists, start with "REG-001"
            if (currentId == null) {
                return "REG-001";
            }

            // Extract the numeric part of the ID
            int numericPart = Integer.parseInt(currentId.split("-")[1]);

            // Increment the numeric part
            numericPart++;

            // Format the new ID with leading zeros
            return String.format("REG-%03d", numericPart);
        }
    }




