## [355. Design Twitter](https://leetcode.com/problems/design-twitter/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

- `Twitter()` Initializes your twitter object.

- `void postTweet(int userId, int tweetId)` Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
- `List<Integer> getNewsFeed(int userId)` Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
- `void follow(int followerId, int followeeId)` The user with ID followerId started following the user with ID followeeId.
- `void unfollow(int followerId, int followeeId)` The user with ID followerId started unfollowing the user with ID followeeId.
 

Example 1:
```
Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
``` 

**Constraints**:

- 1 <= userId, followerId, followeeId <= 500
- 0 <= tweetId <= 104
- All the tweets have unique IDs.
- At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.

## Answers

### Approach 2 - Priority Queue

```java
class Twitter {
    // ============ OO Design ! ============
    // 64ms (99.30%)
    private static int timeStamp = 0;
    private Map<Integer, User> userMap;
    
    // Tweet link to next Tweet so that we can save a lot of time
    // when we execute getNewsFeed(userId)
    private class Tweet {
        public int id;
        public int time;
        public Tweet next;
        public Tweet(int id) {
            this.id = id;
            this.time = timeStamp++;
            next = null;
        }
    }
    
    // OO design so User can follow, unfollow and post itself
    public class User {
        public int id;
        public Set<Integer> followed;
        public Tweet tweet_head;
        
        public User(int id) {
            this.id = id;
            followed = new HashSet();
            follow(id); //first follow itself
            tweet_head = null;
        }
        
        public void follow(int id) {
            followed.add(id);
        }
        public void unfollow(int id) {
            if (followed.contains(id))
                followed.remove(id);
        }
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head;
            tweet_head = t;
        }
    }
    
    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);
    }
    
    // first get all tweets lists from one user including itself and all people it followed.
	// Second add all heads into a max heap. Every time we poll a tweet with 
	// largest time stamp from the heap, then we add its next tweet into the heap.
	// So after adding all heads we only need to add 9 tweets at most into this 
	// heap before we get the 10 most recent tweet.
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList();
        
        if (!userMap.containsKey(userId)) return res;

        PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>(11, (o1, o2) -> o2.time - o1.time);
        // get userId's followees
        Set<Integer> set = userMap.get(userId).followed;
        for (int followedId: set) {
            Tweet t = userMap.get(followedId).tweet_head;
            // very important to take care of NULL
            if (t != null) pq.offer(t);
        }
        
        int i = 0;
        while(!pq.isEmpty() && i < 10) {
            Tweet cur = pq.poll();
            res.add(cur.id);
            i++;
            
            if (cur.next != null) pq.offer(cur.next);
        }
        
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if(!userMap.containsKey(followeeId)){
			User u = new User(followeeId);
			userMap.put(followeeId, u);
		}
        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId==followeeId)
			return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

```

### Approach 1 - Merge N sorted List

```java
class Twitter {
    Map<Integer, User> userIdToUser;
    Map<Integer, Tweet> tweetIdToTweet;
    int timestamp;
    public Twitter() {
        userIdToUser = new HashMap<>();
        tweetIdToTweet = new HashMap<>();
        timestamp = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        User user = userIdToUser.get(userId);
        if (user == null) {
            user = new User(userId);
            userIdToUser.put(userId, user);
        }

        user.postTweet(tweetId, timestamp++);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        if (!userExist(userId)) {
            userIdToUser.put(userId, new User(userId));
        }

        User user = userIdToUser.get(userId);

        return getNewsFeed(user, 10);
    }
    
    public void follow(int followerId, int followeeId) {
        if (!userExist(followerId)) {
            userIdToUser.put(followerId, new User(followerId));
        }
        if (!userExist(followeeId)) {
            userIdToUser.put(followeeId, new User(followeeId));
        }

        User user = userIdToUser.get(followerId);
        user.follow(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (!userExist(followerId)) {
            userIdToUser.put(followerId, new User(followerId));
        }
        if (!userExist(followeeId)) {
            userIdToUser.put(followeeId, new User(followeeId));
        }

        User user = userIdToUser.get(followerId);
        user.unfollow(followeeId);
    }

    /* Helper methods */
    private boolean userExist(int userId) {
        return userIdToUser.containsKey(userId);
    }

    // Merge N sorted list
    private List<Integer> getNewsFeed(User user, int topN) {
        Set<Integer> followees = user.followees;
        List<Tweet>[] tweetsList = new List[followees.size()];
        int idx = 0;
        for (int followeeId : followees) {
            User followee = userIdToUser.get(followeeId);
            tweetsList[idx++] = followee.tweets;
        }
        
        List<Tweet> newsFeed = Helper.mergeTweets(tweetsList);
        int newsFeedSize = newsFeed.size();

        List<Integer> newsFeedIds = new ArrayList<>();
        idx = 0;
        while (idx < topN && idx < newsFeedSize) {
            newsFeedIds.add(newsFeed.get(idx++).tweetId);
        }

        return newsFeedIds;
    }
}

class User {
    int userId;
    Set<Integer> followees;
    LinkedList<Tweet> tweets;

    public User(int id) {
        this.userId = id;
        followees = new HashSet<>();
        tweets = new LinkedList<>();
        this.follow(id);    // first follow himself
    }

    public void follow(int followeeId) {
        this.followees.add(followeeId);
    }

    public void unfollow(int followeeId) {
        this.followees.remove(followeeId);
    }

    public void postTweet(int tweetId, int timestamp) {
        tweets.addFirst(new Tweet(tweetId, timestamp));
    }
}

class Tweet {
    int tweetId;
    int timestamp;

    public Tweet(int tweetId, int timestamp) {
        this.tweetId = tweetId;
        this.timestamp = timestamp;
    }
}

class Helper {
    public static List<Tweet> mergeTweets(List<Tweet>[] tweetsList) {
        return internalMerge(tweetsList, 0, tweetsList.length - 1);
    }

    private static List<Tweet> internalMerge(List<Tweet>[] tweetsList, int start, int end) {
        if (start >= end) {
            return tweetsList[start];
        }

        int mid = (start + end) / 2;

        List<Tweet> left = internalMerge(tweetsList, start, mid);
        List<Tweet> right = internalMerge(tweetsList, mid + 1, end);

        return merge(left, right);
    }

    private static List<Tweet> merge(List<Tweet> left, List<Tweet> right) {
        int lidx = 0, ridx = 0;
        int lsize = left.size(), rsize = right.size();
        List<Tweet> tweets = new LinkedList<>();
        Tweet dummy = new Tweet(-1, -1);

        while (lidx < lsize || ridx < rsize) {
            Tweet ltweet = lidx == lsize ? dummy : left.get(lidx);
            Tweet rtweet = ridx == rsize ? dummy : right.get(ridx);

            if (ltweet.timestamp >= rtweet.timestamp) {
                tweets.add(ltweet);
                lidx++;
            } else {
                tweets.add(rtweet);
                ridx++;
            }
        }

        return tweets;
    }
}
```
