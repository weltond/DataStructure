## Description
- The Hotel has two room type： **SINGLE** and **DOUBLE**

- Using `searchRequest()` to search date and return the available rooms

- It can support reservation

- Users could also cancel reservation

- Using **LRUCache** to store searching result, and using Cache for every search

- Implement `Hotel` and `Room` , we will call `printCache` to test the code.


## Example
Input:
```
Hotel(4)
Room(1, "Single")
Room(2, "Single")
Room(3, "Double")
Room(4, "Double")
searchRequest("2013-01-01", "2013-10-10")
roomsNeeded("Single", 1)
roomsNeeded("Single", 1, "Double", 2)
roomsNeeded("Single", 1)
reservationRequest("2013-01-06", "2013-10-10", 2)
```
Output:
```
Printing Cache ...
Search Request -> Start date is: Jan 1, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
Value -> For room type: DOUBLE, available rooms are: 3; 4; . For room type: SINGLE, available rooms are: 1; 2; . 

*****************************

Printing Cache ...
Search Request -> Start date is: Jan 1, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
Value -> For room type: DOUBLE, available rooms are: 3; 4; . For room type: SINGLE, available rooms are: 1; 2; . 

Search Request -> Start date is: Jan 6, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
Value -> For room type: DOUBLE, available rooms are: . For room type: SINGLE, available rooms are: 1; 2; . 

*****************************
```

## TO DO
```java
import java.util.Map.Entry;

public class Hotel {
    public static final int DAY = 1*24*60*60*1000;
    
	private List<Room> rooms;
	public LRUCache cache;
	
	public Hotel(int cacheSize)
	{
		// Write your code here
	}
	
	public Reservation makeReservation(ReservationRequest request)
	{
		// Write your code here
	}
	
	public Map<RoomType, List<Room>> handleSearchResult(SearchRequest request)
	{
		// Write your code here
	}
	
	public void cancelReservation(Reservation reservation)
	{
		for(Room room : reservation.getRooms())
		{
			room.cancelReservation(reservation);
		}
	}
	
	public List<Room> getRooms()
	{
		// Write your code here
	}
	
	private Map<RoomType, List<Room>> getAvailableRooms(SearchRequest request)
	{
		// Write your code here
	}
	
	public String printCache()
	{
		return "Printing Cache ...\n" + cache.toString() +
	 	       "*****************************\n";
	}
}


class Room {
    
    public static final int DAY = 1*24*60*60*1000;
    
	private int id;
	private RoomType roomType;
	private Set<Date> reservations;
	
	public Room(int id, RoomType roomType)
	{
		// Write your code here
	}
	
	public boolean isValidRequest(SearchRequest request)
	{
		// Write your code here
	}
	
	public void makeReservation(Date startDate, Date endDate)
	{
		// Write your code here
	}
	
	public RoomType getRoomType()
	{
		// Write your code here
	}
	
	public int getId()
	{
		// Write your code here
	}
	
    public void cancelReservation(Reservation reservation)
	{
		Date date = new Date(reservation.getStartDate().getTime());
		for (; date.before(reservation.getEndDate()); date.setTime(date.getTime() + DAY))
		{
			Date tempDate = new Date(date.getTime());
			reservations.remove(tempDate);
		}
	}
}

class LRUCache extends LinkedHashMap<SearchRequest, Map<RoomType, List<Room>>>{

	private static final long serialVersionUID = 1L;
	private int capacity;
	
	public LRUCache(int capacity)
	{
		super(capacity);
		this.capacity = capacity;
	}
	
	@Override
	protected boolean removeEldestEntry(Entry<SearchRequest, Map<RoomType, List<Room>>> eldest) {
		// TODO Auto-generated method stub
		return size() > this.capacity;
	}
	
	private String printAvailableRooms(Map<RoomType, List<Room>> rooms)
	{
		String res = "";
		for(Entry<RoomType, List<Room>> entry : rooms.entrySet())
		{
			res += "For room type: " + entry.getKey() + ", available rooms are: ";
			for(Room room : entry.getValue())
			{
				res += room.getId() + "; ";
			}
			res += ". ";
		}
		return res;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String res = "";
		
		for(Entry<SearchRequest, Map<RoomType, List<Room>>> entry : super.entrySet())
		{
			res += ("Search Request -> " + entry.getKey().toString() + "\n");
			res += ("Value -> " + printAvailableRooms(entry.getValue()) + "\n");
			res += "\n";
		}

		return res;
	}
}

class ReservationRequest {
	private Date startDate;
	private Date endDate;
	private Map<RoomType, Integer> roomsNeeded;
	
	public ReservationRequest(Date startDate, Date endDate, Map<RoomType, Integer> roomsNeeded) {
		// TODO Auto-generated constructor stub
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomsNeeded = roomsNeeded;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public Map<RoomType, Integer> getRoomsNeeded()
	{
		return roomsNeeded;
	}
}

class Reservation {
	private Date startDate;
	private Date endDate;
	private List<Room> rooms;
	
	public Reservation(Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		rooms = new ArrayList<>();
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public List<Room> getRooms()
	{
		return rooms;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		
		String res = "Start date is: " + startDate.toLocaleString() + ", End date is: " + endDate.toLocaleString()
			+ ", rooms are: ";
		
		for(Room room : rooms)
		{
			res += room.getId() + "; ";
		}
		res += ". ";
		
		return res;
	}
}

enum RoomType {
	SINGLE,
	DOUBLE
}

class SearchRequest {
	private Date startDate;
	private Date endDate;
	
	public SearchRequest(Date startDate, Date endDate) {
		// TODO Auto-generated constructor stub
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String res = "Start date is: " + startDate.toLocaleString() + ", End date is: " + endDate.toLocaleString();
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this) return true;
		if(!(obj instanceof SearchRequest)) return false;
		
		SearchRequest request = (SearchRequest) obj;
		
		return request.startDate == this.startDate && request.endDate == this.endDate;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = 31 * result + startDate.hashCode();
		result = 31 * result + endDate.hashCode();
		return result;
	}
}
```

## Tag
**OO Design**