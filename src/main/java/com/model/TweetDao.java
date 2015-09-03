package com.model;

import java.util.List;

/**
 * Created by kumarke on 9/1/15.
 */

public interface TweetDao {

    //Read
    public Tweet getById(int id);

    //Get All
    public List<Tweet> getAll();

    //Get by user_id
    public List<Tweet> getByUserId(int user_id);

    //Tweets from all users followed by give user
    public List<Tweet> getFeedByUserId(int followed_by_user);
}

