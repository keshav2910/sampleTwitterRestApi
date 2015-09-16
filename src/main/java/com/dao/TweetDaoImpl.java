package com.dao;

import com.model.Tweet;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kumarke on 9/1/15.
 */


public class TweetDaoImpl implements TweetDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(TweetDaoImpl.class);

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Tweet getById(int id) {
        String query = "select id,text,user_id from tweet where id=?";
        Tweet tweet = this.jdbcTemplate.queryForObject(query,new Object[]{id}, new TweetMapper());
        return tweet;
    }

    public List<Tweet> getAll() {
        String query = "select id,text,user_id from tweet";
        List<Tweet> tweetList = this.jdbcTemplate.query(query, new TweetMapper());
        return tweetList;
    }

    public List<Tweet> getByUserId(int userId){
        String query = "select id,text,user_id from tweet where user_id=?";
        List<Tweet> tweetList;
        try {
            tweetList = this.jdbcTemplate.query(query, new Object[]{userId}, new TweetMapper());
        }catch(DataAccessException da)
        {
            throw new RuntimeException();
        }
        return tweetList;
    }

    public List<Tweet> getFeedByUserId(int followed_by_user) {
        String query = "SELECT * FROM tweet WHERE user_id IN(SELECT follows FROM follow WHERE user_id=?)";
        List<Tweet> tweetList;
        try {
            tweetList = this.jdbcTemplate.query(query, new Object[]{followed_by_user}, new TweetMapper());
        }catch(DataAccessException da)
        {
            throw new RuntimeException();
        }
        return tweetList;
    }

    private static final class TweetMapper implements RowMapper<Tweet> {
        public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tweet tweet = new Tweet();
            tweet.setText(rs.getString("text"));
            tweet.setId(rs.getInt("id"));
            tweet.setUser_id(rs.getInt("user_id"));
            return tweet;
        }
    }
}
