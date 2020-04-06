package tickets;

public class ConcertTicket extends Ticket {

	
	private int seat;

	public ConcertTicket(int seat, float price) {
		super();
		this.seat = seat;
		setPrice(price);
	}

	@Override
	public float calcTicketPrice() {
		return getPrice() + (getPrice() / seat);
	}

}
