package org.example.zad5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserDummyRepository {
    List<User> userList = new ArrayList<>();

    public UserDummyRepository() {
        User user = new User(1L,"John Doe","Test");
        userList.add(user);
    }

    public List<User> findAll() {
        return userList;
    }

    public User findById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User save(User newUser) {
        if (newUser.getId() == null) {
            Random random = new Random();
            newUser.setId(random.nextLong());
            userList.add(newUser);
        } else {
            userList.stream().filter(user -> user.getId().equals(newUser.getId())).findFirst().ifPresent(user -> {
                user.setLastName(newUser.getLastName());
                user.setName(newUser.getName());
            });
        }
        return newUser;
    }

    public void deleteById(Long id) {
        userList.removeIf(user -> user.getId().equals(id));
    }
}
