package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // diferença entre duas data em milisegundos
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);   // converte milisegundos em dias
	}
	
	public void updateDates(Date checkIn, Date checkOut) {		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw  new IllegalArgumentException("Reservation dates for update must be future dates");  // essa exceção é utilizada quando existe erro na passagem de argumnento
		}
		if (!checkOut.after(checkIn)) {
			throw new IllegalArgumentException("Check-out date must be after check-in date");				
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room "  
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn) 
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";				
	}
	
	
	
}
