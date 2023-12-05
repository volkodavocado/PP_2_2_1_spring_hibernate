package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    String addUserQuery = "FROM User WHERE firstName = :firstName and lastName = :lastName and email = :email";
    String getListOfUsersQuery = "from User";
    String getUsersByCarQuery = "from User u where u.car.model = :model and u.car.series = :series";

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(addUserQuery);

            query.setParameter("firstName", user.getFirstName());
            query.setParameter("lastName", user.getLastName());
            query.setParameter("email", user.getEmail());

            List<User> existingUsers = query.getResultList();

            if (existingUsers.isEmpty()) {
                session.save(user);
            } else {
                System.out.println("Пользователь уже существует");
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(getListOfUsersQuery);
            return query.getResultList();
        }
    }

    @Override
    public List<User> getUsersByCar(Integer series, String model) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(getUsersByCarQuery);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.list();
        }
    }
}
