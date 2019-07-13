# sampleTwitterRestApi
sample twitter REST API's

Following API's return JSON response:
1. /tweets : all tweets
2. /tweets?user_id={user_id} : tweets for a given user_id
3. /tweets?followed_by_user={user_id} : tweets from all users followed by given user_id
4. /tweet/{tweet-id} : tweet with given id


Eg: 
/tweets
/tweets?user_id=1
/tweets?followd_by_user=1
/tweet/1

