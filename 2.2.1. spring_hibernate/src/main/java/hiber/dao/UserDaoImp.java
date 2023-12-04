package hiber.dao;

import hiber.model.User;
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

    String hqlAdd = "FROM User WHERE firstName = :firstName and lastName = :lastName and email = :email";
    String hqlGetListOfUsers = "from User";
    String hqlGetUsersByCar = "from User u where u.car.model = :model and u.car.series = :series";

    @Override
    public void create(User user) {//todo codeStyle - правильное именование метода и query -> createUserQuery например (везде по коду)
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlAdd);//todo реализовать в try_with_resources во всех методах
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("email", user.getEmail());
        List<User> existingUsers = query.getResultList();
        if (existingUsers.isEmpty()) {
            sessionFactory.getCurrentSession().save(user);
        } else {
            System.out.println("Пользователь уже существует");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession().createQuery(hqlGetListOfUsers);
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByCar(int series, String model) {//todo избавляемся от примитивов - int -> Integer по коду
        Query<User> query = sessionFactory
                .getCurrentSession()
                .createQuery(hqlGetUsersByCar);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.list();
    }
}
