package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

    @JsonProperty("row")
    private int row;
    @JsonProperty("column")
    private int column;
    @JsonProperty("price")
    private int price;


    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = setPrice();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public int setPrice() {
        if (this.row <= 4) {
            return 10;
        }
        return 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (getRow() != seat.getRow()) return false;
        return getColumn() == seat.getColumn();
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getColumn();
        return result;
    }
}
