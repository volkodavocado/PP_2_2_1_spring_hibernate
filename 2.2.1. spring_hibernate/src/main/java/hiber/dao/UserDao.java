package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    List<User> getListUsers();

    List<User> getUsersByCar(int series, String model);
}
