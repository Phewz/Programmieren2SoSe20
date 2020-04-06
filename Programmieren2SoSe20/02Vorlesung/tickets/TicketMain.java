package tickets;

public class TicketMain {

	public static void main(String[] args) {
		
		Ticket[] tickets = new Ticket[2];
		tickets[0] = new CinemaTicket(90, 14);
		tickets[1] = new ConcertTicket(24, 5);
		
		for(Ticket i: tickets) {
			System.out.println(i.calcTicketPrice());
		}

	}

}
