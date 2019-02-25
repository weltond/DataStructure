## Description
- Every player has `1000` casino token to start
- The dealer has `10000` casino token
- If the player wins，that one get double token
- If the dealer wins，deal will take the token from player
- If the points are the same, then the dealer wins
- A could be considered as `1` or `11`

## Example
```
Player(10)
Player(100)
Player(500)
Card([1,4,2,3,1,4,2,3,9,10])
InitialCards()
compareResult()
```
You should return below:
```
playerid: 1 ;Cards: 1 , 1; Current best value is: 12, current bets: 10, total bets: 990
playerid: 2 ;Cards: 4 , 4; Current best value is: 8, current bets: 100, total bets: 900
playerid: 3 ;Cards: 2 , 2; Current best value is: 4, current bets: 500, total bets: 500
Dealer Cards: 3 , 3; Current best value is: 6, total bets: 10000
playerid: 1 ;Cards: 1 , 1; Current best value is: 12, current bets: 0, total bets: 1010
playerid: 2 ;Cards: 4 , 4; Current best value is: 8, current bets: 0, total bets: 1100
playerid: 3 ;Cards: 2 , 2; Current best value is: 4, current bets: 0, total bets: 500
Dealer Cards: 3 , 3; Current best value is: 6, total bets: 10390
```

## TO DO
```java
public class BlackJack {
    /**
     * @return: nothing
     */
    public void initCards(List<Card> cards) {
        // 
    }
}
```

## Tag
**OO Design**
