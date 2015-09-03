package com.model;

import java.util.List;

/**
 * Created by kumarke on 9/1/15.
 */

public interface TweetDao {
    //Create
    public void save(Tweet tweet);
    //Read
    public Tweet getById(int id);
    //Update
    public void update(Tweet tweet);
    //Delete
    public void deleteById(int id);
    //Get All
    public List<Tweet> getAll();
    //Get by user_id
    public List<Tweet> getByUserId(int user_id);
}

