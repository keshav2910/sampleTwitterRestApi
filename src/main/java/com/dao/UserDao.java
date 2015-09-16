package com.dao;

import java.util.List;

/**
 * Created by kumarke on 9/3/15.
 */
public interface UserDao {
    //Read
     UserDao getById(int id);
    //Get All
     List<UserDao> getAll();
    //Get by user_id
     List<UserDao> getByUserId(int user_id);
}
