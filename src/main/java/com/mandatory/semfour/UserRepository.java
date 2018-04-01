package com.mandatory.semfour;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris on 31-03-18.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
