package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
    void createUser(User user);

    List<User> getListUsers();

    List<User> getUsersByCar(Integer series, String model);
}
