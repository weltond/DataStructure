## Description
- The Vending Machine has three states：`NoSelection`, `HasSelection`, `InsertedMoney`
- The Vending Machine sells three drink：`Coke`, `Sprite` and `MountainDew`
- The Vending Machine should print the correct information in correct state

## Example
```
select("Coke")
select("Sprite")
insert(500)
execTrans()
```
You should return below:
```
Current selection is: Coke, current inserted money: 0, current state is : HasSelection
Current selection is: Sprite, current inserted money: 0, current state is : HasSelection
Current selection is: Sprite, current inserted money: 500, current state is : InsertedMoney
Current selection is: null, current inserted money: 0, current state is : NoSelection
```

## TO DO
```java
public class VendingMachine {
    /**
     * @return: nothing
     */
    public void setSelectedItem(String item) {
        // 
    }
}
```

## Tag
**OO Design**
