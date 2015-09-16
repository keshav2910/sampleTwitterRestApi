package com.dao;

import com.model.Tweet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kumarke on 9/1/15.
 */

public interface TweetDao {

    //Read
    Tweet getById(int id);

    //Get All
    List<Tweet> getAll();

    //Get by user_id
    List<Tweet> getByUserId(int user_id);

    //Tweets from all users followed by give user
    List<Tweet> getFeedByUserId(int followed_by_user);
}

