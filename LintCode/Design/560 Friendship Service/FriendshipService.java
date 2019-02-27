public class FriendshipService {
    Map<Integer, Set<Integer>> followers;
    Map<Integer, Set<Integer>> followings;
    public FriendshipService() {
        this.followers = new HashMap<Integer, Set<Integer>>();
        this.followings = new HashMap<Integer, Set<Integer>>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        if (!this.followers.containsKey(user_id)) return new ArrayList<>();
        
        return new ArrayList<Integer>(this.followers.get(user_id));
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        if (!this.followings.containsKey(user_id)) return new ArrayList<>();

        return new ArrayList<Integer>(this.followings.get(user_id));
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        this.followers.computeIfAbsent(to_user_id, k -> new TreeSet<Integer>()).add(from_user_id);
        this.followings.computeIfAbsent(from_user_id, k -> new TreeSet<Integer>()).add(to_user_id);
        
        // if (!followers.containsKey(to_user_id))
        //     followers.put(to_user_id, new TreeSet<Integer>());

        // followers.get(to_user_id).add(from_user_id);

        // if (!followings.containsKey(from_user_id))
        //     followings.put(from_user_id, new TreeSet<Integer>());

        // followings.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        if (!this.followers.containsKey(to_user_id) || !this.followings.containsKey(from_user_id)) return;
        
        this.followings.get(from_user_id).remove(to_user_id);
        this.followers.get(to_user_id).remove(from_user_id);
    }
}