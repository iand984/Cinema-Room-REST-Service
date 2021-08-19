package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ticket {

    private Seat seat;
    private UUID token;
    private List<UUID> soldTickets = new ArrayList<>();

    public Ticket() {
    }


    public Ticket(Seat seat) {
        token = UUID.randomUUID();
        this.seat = seat;
    }


    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

}
