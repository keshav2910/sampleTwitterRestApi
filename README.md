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


DATABASE:

mysql> select * from user;
+----+-----------+-------------------+
| id | name      | email             |
+----+-----------+-------------------+
|  1 | keshav    | keshav@riva.co    |
|  2 | sheetal   | sheetal@riva.co   |
|  3 | shilp     | shilp@riva.co     |
|  4 | devashish | devashish@riva.co |
+----+-----------+-------------------


mysql> SELECT * FROM follow;
+---------+---------+
| user_id | follows |
+---------+---------+
|       1 |       3 |
|       2 |       3 |
|       1 |       4 |
|       2 |       4 |
|       3 |       4 |
+---------+---------+


mysql> select * from tweet;
+----+-------------------------+---------+
| id | text                    | user_id |
+----+-------------------------+---------+
|  1 | this is my first tweet  |       1 |
|  2 | this is my second tweet |       1 |
|  3 | this is my third tweet  |       1 |
|  4 | im feeling lucky        |       2 |
|  5 | im feeling happy        |       2 |
|  6 | ubuntu is awesome       |       3 |
|  7 | mac is great            |       3 |
|  8 | flock is great          |       4 |
|  9 | ringo is awesome        |       4 |
+----+-------------------------+---------+
+
