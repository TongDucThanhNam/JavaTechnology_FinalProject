package com.tdtu.finalproject;

import com.tdtu.finalproject.model.User;
import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.username = :username")
    public User getUserByUsername(@Param("username") String username);
}