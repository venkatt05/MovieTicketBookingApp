package com.myticket.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.myticket.database.UserRepository;
import com.myticket.models.Ticket;
import com.myticket.models.movies.ScreenD;

@Service("screen4")
public class TicketBookingServiceForScreenD implements TicketBookingService {

	private ScreenD screenD;
	private Ticket ticket;

	@Autowired
	private UserRepository database;

	@Autowired
	@Lazy
	public TicketBookingServiceForScreenD(ScreenD screenD, Ticket ticket) {

		this.screenD = screenD;
		this.ticket = ticket;

	}

	@Override
	public List<String> getAvailableSeats() {

		return screenD.ticketsAvailable();
	}

	@Override
	public void cancelTicket(String seatNumber) {

		screenD.cancelTicket(seatNumber);

	}

	@Override
	public void setTicketsForBooking(Set<String> ticketsToBook, String mailId) {

		screenD.bookTicket(ticketsToBook);

		ticket.setMovieName(screenD.getMovieName());

		ticket.setSeatNumber(ticketsToBook);

		ticket.setTotalCost(ticketsToBook.size());

		database.putTicket(mailId, ticket.getSeatNumbers().toString(), ticket.getTotalCost(), screenD.getMovieName(),
				"Screen - D");
	}

}
