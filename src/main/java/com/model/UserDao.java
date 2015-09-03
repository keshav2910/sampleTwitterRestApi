package com.model;

import java.util.List;

/**
 * Created by kumarke on 9/3/15.
 */
public interface UserDao {
    //Read
    public UserDao getById(int id);
    //Get All
    public List<UserDao> getAll();
    //Get by user_id
    public List<UserDao> getByUserId(int user_id);

}
