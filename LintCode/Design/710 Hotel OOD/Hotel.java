import java.util.Map.Entry;

public class Hotel {
    public static final int DAY = 1*24*60*60*1000;
    
    private List<Room> rooms;
	private LRUCache cache;
	
	public Hotel(int cacheSize)
	{
		cache = new LRUCache(cacheSize);
		rooms = new ArrayList<>();
	}
	
	public Reservation makeReservation(ReservationRequest request)
	{
		Reservation reservation = new Reservation(request.getStartDate(), request.getEndDate());
		
		SearchRequest search = new SearchRequest(request.getStartDate(), request.getEndDate());
		
		Map<RoomType, List<Room>> roomsAvailable = getAvailableRooms(search);
		
		Map<RoomType, Integer> roomsNeeded = request.getRoomsNeeded();
		for(Entry<RoomType, Integer> entry : roomsNeeded.entrySet())
		{
			RoomType roomType = entry.getKey();
			int roomCount = entry.getValue();
			
			List<Room> rooms = roomsAvailable.get(roomType);
			
			//Not enough rooms
			if(entry.getValue() > rooms.size())
			{
				cache.put(search, roomsAvailable);
				return null;
			}
			
			for(int i = 0; i < roomCount; i++)
			{	
				Room room = rooms.remove(0);
				reservation.getRooms().add(room);
			}
			
			roomsAvailable.put(entry.getKey(), rooms);
		}
		
		cache.put(search, roomsAvailable);
		
		for(Room room : reservation.getRooms())
		{
			room.makeReservation(reservation.getStartDate(), reservation.getEndDate());
		}
		
		return reservation;
	}
	
	public Map<RoomType, List<Room>> handleSearchResult(SearchRequest request)
	{
		if(cache.containsKey(request))
		{
			return cache.get(request);
		}
		
		Map<RoomType, List<Room>> res = getAvailableRooms(request);
		
		cache.put(request, res);
		
		return res;
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
		return rooms;
	}
	
	private Map<RoomType, List<Room>> getAvailableRooms(SearchRequest request)
	{
		Map<RoomType, List<Room>> res = new HashMap<>();
		
		res.put(RoomType.SINGLE, new ArrayList<>());
		res.put(RoomType.DOUBLE, new ArrayList<>());
		
		for(Room room : rooms)
		{
			if(room.isValidRequest(request))
			{
				List<Room> roomList = res.get(room.getRoomType());
				roomList.add(room);
				res.put(room.getRoomType(), roomList);
			}
		}
		
		return res;
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
		this.id = id;
		this.roomType = roomType;
		reservations = new HashSet<Date>();
	}
	
	public boolean isValidRequest(SearchRequest request)
	{
		Date date = new Date(request.getStartDate().getTime());
		for (; date.before(request.getEndDate()); date.setTime(date.getTime() + DAY))
		{
			Date tempDate = new Date(date.getTime());
			if(reservations.contains(tempDate))
			{
				return false;
			}
		}
		return true;
	}
	
	public void makeReservation(Date startDate, Date endDate)
	{
		Date date = new Date(startDate.getTime());
		for (; date.before(endDate); date.setTime(date.getTime() + DAY))
		{
			Date tempDate = new Date(date.getTime());
			reservations.add(tempDate);
		}
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
	
	public RoomType getRoomType()
	{
		return roomType;
	}
	
	public int getId()
	{
		return this.id;
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
		
		return request.startDate .equals(this.startDate) && request.endDate.equals(this.endDate);
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