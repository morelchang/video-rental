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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = _rentals.iterator();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasNext()) {
            Rental each = (Rental) rentals.next();

            int bonusPoints = getFrequentRenterPoints(each);
            frequentRenterPoints += bonusPoints;

            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }

        // add footer lines
        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }

    private int getFrequentRenterPoints(Rental each) {
        // add frequent renter points
        int bonusPoints = 1;
        // add bonus for a two day new release rental
        if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1)
            bonusPoints++;
        return bonusPoints;
    }
}
