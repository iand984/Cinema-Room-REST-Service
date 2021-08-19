package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {

    private Seat seat;

    public ReturnedTicket(Seat seat) {
        this.seat = seat;
    }

    @JsonProperty("returned_ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
