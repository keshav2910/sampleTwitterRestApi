package com.service;

import com.dao.TweetDao;
import com.exception.AppException;
import com.model.Tweet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by kumarke on 9/14/15.
 */

@Service
public class TweetManagerImpl implements TweetManager {

    @Autowired
    TweetDao tweetDao;

    private static final Logger logger = Logger.getLogger(TweetManagerImpl.class);

    public Tweet getById(int id) {
        Tweet tweet;
        try {
            tweet = tweetDao.getById(id);
        }catch(EmptyResultDataAccessException ee){
            logger.info("No records found", ee);
            throw new AppException(ee.getMessage(),404,"Not Found");
        }
        catch (RuntimeException re) {
            logger.error("Error retrieving data from db", re);
            throw new AppException(re.getMessage(),500,"Error retrieving data from db");
        }
        return tweet;
    }

    public List<Tweet> getAll() {
        List<Tweet> tweetList;
        try {
            tweetList = tweetDao.getAll();
        }
        catch(EmptyResultDataAccessException ee){
            logger.info("No records found", ee);
            throw new AppException(ee.getMessage(),404,"Not Found");
        }
        catch (RuntimeException re) {
            logger.error("Error retrieving data from db", re);
            throw new AppException(re.getMessage(),500,"Error retrieving data from db");
        }
        return tweetList;
    }

    public List<Tweet> getByUserId(int user_id) {
        return tweetDao.getByUserId(user_id);
    }

    public List<Tweet> getFeedByUserId(int followed_by_user) {
        return tweetDao.getFeedByUserId(followed_by_user);
    }
}
