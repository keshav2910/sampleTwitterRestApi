package com.controller;

import com.model.Tweet;
import com.service.TweetManager;
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
public class TweetController extends BaseController{

    private ClassPathXmlApplicationContext ctx;

    private TweetManager tweetManager ;

    private static final Logger logger = Logger.getLogger(TweetController.class);

    TweetController(){
        ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        tweetManager = (TweetManager)ctx.getBean("tweetManager");
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public List<Tweet> getTweets() {
       return tweetManager.getAll();
    }

    @RequestMapping(value = "/tweets/{id}", method = RequestMethod.GET)
    public Tweet getTweetById(@PathVariable int id) {
        return tweetManager.getById(id);
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET, params = {"user_id"})
    public ResponseEntity<List<Tweet>> getTweetByUserId(@RequestParam int user_id) {
        List<Tweet> tweetList = null;
        HttpStatus statusCode;
        try {
            tweetList = tweetManager.getByUserId(user_id);
            if (tweetList.isEmpty())
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        } catch (RuntimeException re) {
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Tweet>>(tweetList, statusCode);
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET, params = {"followed_by_user"})
    public ResponseEntity<List<Tweet>> getFeedByUserId(@RequestParam int followed_by_user) {
        List<Tweet> tweetList = null;
        HttpStatus statusCode;
        try {
            tweetList = tweetManager.getFeedByUserId(followed_by_user);
            if (tweetList.size() == 0)
                statusCode = HttpStatus.NOT_FOUND;
            else
                statusCode = HttpStatus.OK;
        } catch (RuntimeException re) {
            logger.error("Some error occurred", re);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Tweet>>(tweetList, statusCode);
    }
}
