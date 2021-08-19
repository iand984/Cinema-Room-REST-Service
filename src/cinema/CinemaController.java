package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    private Cinema cinema = new Cinema(9, 9);
    private List<Ticket> ticketList = new ArrayList<>();
    private int currentIncome = 0;


    @GetMapping("/seats")
    public Cinema getSeats() {
        return this.cinema;
    }

    @PostMapping("/purchase")
    public @ResponseBody
    ResponseEntity<?> givePurchasedSeat(@RequestBody Seat seat) {

        if (seat.getRow() > 9 || seat.getRow() < 1 || seat.getColumn() > 9 || seat.getColumn() < 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "The number of a row or a column is out of bounds!"));
        }

        if (!this.cinema.getAvailableSeats().contains(seat)) {
            return ResponseEntity.badRequest().body(Map.of("error", "The ticket has been already purchased!"));
        }

        Ticket ticket = new Ticket(seat);

        ticketList.add(ticket);
        currentIncome += ticket.getSeat().getPrice();
        this.cinema.bookSeat(seat);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/return")
    public @ResponseBody
    ResponseEntity<?> refundTicket(@RequestBody Ticket soldTicket) {

        for (Ticket ticket : ticketList) {
            if (ticket.getToken().equals(soldTicket.getToken())) {
                ReturnedTicket returnedTicket = new ReturnedTicket(ticket.getSeat());
                this.cinema.unbookedSeat(ticket.getSeat());
                currentIncome -= ticket.getSeat().getPrice();
                return ResponseEntity.ok(returnedTicket);
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
    }

    @PostMapping("/stats")
    public @ResponseBody
    ResponseEntity<?> giveStats(@RequestParam(required = false) String password) {

        if (password == null) {
            return ResponseEntity.status(401).body(Map.of("error", "The password is wrong!"));
        }
        return ResponseEntity.ok(Map.of("current_income", this.currentIncome, "number_of_available_seats", cinema.getAvailableSeats().size(), "number_of_purchased_tickets", cinema.getBookedSeats().size()));
    }

}
