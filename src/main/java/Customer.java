import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    private String _name;
    private List<Rental> _rentals = new ArrayList<>();

    public Customer(String name) {
        this._name = name;
    }

    public void addRental(Rental rental) {
        _rentals.add(rental);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = _rentals.iterator();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasNext()) {
            Rental each = (Rental) rentals.next();

            // add frequent renter points
            frequentRenterPoints += each.getFrequentRenterPoints();

            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        // add footer lines
        result += "You owed " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }

    private double getTotalCharge() {
        double totalAmount = 0;
        Iterator<Rental> rentals = _rentals.iterator();
        while (rentals.hasNext()) {
            Rental each = (Rental) rentals.next();
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }
}
