package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int totalRows;
    private int totalColumns;
    private List<Seat> allSeats = new ArrayList<>();
    private List<Seat> bookedSeats = new ArrayList<>();

    public Cinema() {
    }

    public Cinema(int total_rows, int total_columns) {
        this.totalRows = total_rows;
        this.totalColumns = total_columns;

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                this.allSeats.add(new Seat(i, j));
            }
        }
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(bookedSeats);
        return availableSeats;
    }

    public Seat bookSeat(Seat seat) {
        bookedSeats.add(seat);
        return seat;
    }

    public void unbookedSeat(Seat seat) {
        bookedSeats.remove(seat);
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonIgnore
    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }



}
