package com.mandatory.semfour.repository;

import com.mandatory.semfour.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chris on 31-03-18.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public String findByUsername(String username);
    public String findByEmail(String email);

}
