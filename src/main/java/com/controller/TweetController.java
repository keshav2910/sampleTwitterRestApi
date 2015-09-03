package com.controller;

import com.model.Tweet;
import com.model.TweetDao;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by kumarke on 8/28/15.
 */

@RestController
public class TweetController{

    private ClassPathXmlApplicationContext ctx;
    private TweetDao tweetDao;
    private static final Logger logger = Logger.getLogger(TweetController.class);

    public TweetController() {
        ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        this.tweetDao = (TweetDao)ctx.getBean("tweetDao");
    }

    @RequestMapping(value="/tweets", method=RequestMethod.GET)
    public ResponseEntity<List<Tweet>> getTweets(){
        List<Tweet> tweets = null;
        HttpStatus statusCode;
        try {
            tweets = tweetDao.getAll();
            if(tweets.size()==0)
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        }catch(RuntimeException re)
        {
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Tweet>>(tweets,statusCode);

    }

    @RequestMapping(value="/tweet/{id}", method=RequestMethod.GET)
    public ResponseEntity<Tweet> getTweetById(@PathVariable int id){
        Tweet tweet = null;
        HttpStatus statusCode;
        try{
            tweet = tweetDao.getById(id);
            if(tweet == null)
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        }catch(RuntimeException re){
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Tweet>(tweet,statusCode);
    }

    @RequestMapping(value="/tweets", method=RequestMethod.GET,params = {"user_id"})
    public ResponseEntity<List<Tweet>>  getTweetByUserId(@RequestParam int user_id){
        List<Tweet> tweetList =  null;
        HttpStatus statusCode;
        try {
            tweetList = tweetDao.getByUserId(user_id);
            if(tweetList.size() == 0)
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        }catch(RuntimeException re){
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Tweet>>(tweetList, statusCode);
    }

    @RequestMapping(value="/tweets", method=RequestMethod.GET, params = {"followed_by_user"})
    public  ResponseEntity<List<Tweet>> getFeedByUserId(@RequestParam int followed_by_user){
        List<Tweet> tweetList =  null;
        HttpStatus statusCode;
        try {
            tweetList = tweetDao.getFeedByUserId(followed_by_user);
            if(tweetList.size() == 0)
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        }catch(RuntimeException re){
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Tweet>>(tweetList, statusCode);
    }
}
