package com.eshi.addis.security.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    User createUser(@RequestBody() User user);

    User updateUser(long userId, @RequestBody() User user);

    User getUser(long userId);

    void deleteUser(long userId);

    User resetPassword(long userId, @RequestBody() UserPasswordDTO passwordReset);


    Page<User> getUsers(Pageable pageable);
}
