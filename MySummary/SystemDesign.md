# Jiuzhang
## 4S - Scenario, Service, Storage, Scale
### Scenario
### Service
### Storage
#### DataBase
- MySQL / ProsgreSQL: ~1k QPS
- MongoDB / Cassandra (disk NoSQL): ~10k QPS
- Redis / Memcached (memory NoSQL): ~100k - 1m QPS
#### Cache
- What is cache
  - Like HashMap in JAVA
  - key-value structure
- software
  - Memcached (does not support persistency) : Cache-aside -> usually used?
  - Redis (support persistency): Cache-through

#### SQL vs NoSQL 
1. In general case, both are OK.
2. SQL if need **Transaction**
3. SQL can help you do a lot like **Serialization** and **Secondary Index** while NoSQL is done by developer
4. disk NoSQL is 10 times faster than SQL

1. Transaction ? -> SQL
2. SQL Query? -> SQL
3. Lazy developer? -> SQL. Most web framework is compatible with SQL.
4. Sequential ID ? -> SQL. NoSQL doesn't support sequential.
5. High QPS? -> NoSQL
6. Scalability? -> NoSQL. It helps you do the rest.

- Cassandra
  - row key: aka **hash key**. Doesn't support range query. Usually is used as `user_id`
  - Column key: Sorted. Support range query. Can be `timestamp` + `user_id`
  - Value: Usually data like String. Need to be serialized.
  - e.g. FriendShip Table: 
    - row_key: user_id
    - column_key: friend_user_id
    - Value: <is_mutual_friend, is_blocked, timestamp>
    
### Scale
- Single Point Failure
  - Sharding: shard data and save them onto different machines. NoSQL is auto while SQL is not.
    - Vertical: based on column
    - Horizontal: based on row
      - **Consistant Hashing**
        - Data structure: TreeMap
        - Micro shards / Virtual nodes. Numbers of them are traded off for search efficientcy.
  - Replica
    - MySQL:
      - "auto": Master - slave
      - "Manual": clock-wise 3 nodes in consistent hashing circle
    - NoSQL:
      - "auto": clock-wise 3 nodes in consistent hashing circle
      - "manual": no need
