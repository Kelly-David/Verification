package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 01/02/2018.
 */
public class Rate {
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyDiscountedRate;
    private ArrayList<Period> discount = new ArrayList<>();
    private ArrayList<Period> normal = new ArrayList<>();
    private final CalculateChargeStrategy visitorRate = new VisitorBehaviour();
    private final CalculateChargeStrategy studentRate = new StudentBehaviour();
    private final CalculateChargeStrategy staffRate = new StaffBehaviour();
    private final CalculateChargeStrategy managementRate = new ManagementBehaviour();

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods
            , ArrayList<Period> normalPeriods) {
        if (discountPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        // Task 3 - Check for arrayList of null
        if(discountPeriods.contains(null) || normalPeriods.contains(null)) {
            throw new IllegalArgumentException("ArrayList cannot contain null");
        }
        if (normalRate == null || discountedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || discountedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(discountedRate) < 0) {
            // Task 3 - Clarification
            throw new IllegalArgumentException("The normal rate cannot be less than discounted rate");
        }
        if (!isValidPeriods(discountPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(discountPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.kind = kind;
        this.hourlyNormalRate = normalRate;
        this.hourlyDiscountedRate = discountedRate;
        this.discount = discountPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1
     * @param periods2
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid) {
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            Period secondPeriod;
            int i = 0;
            int lastIndex = list.size()-1;
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * checks if a period is a valid addition to a collection of periods
     * @param period the cm.Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collection of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }

    /**
     * calculates the total charge for the period
     * @param periodStay the cm.Period
     * @return BigDecimal value total charge
     */
    public BigDecimal calculate(Period periodStay) {
        int normalRateHours = periodStay.occurences(normal);
        int discountRateHours = periodStay.occurences(discount);
        BigDecimal rate = (this.hourlyNormalRate
                .multiply(BigDecimal.valueOf(normalRateHours)))
                .add(this.hourlyDiscountedRate
                        .multiply(BigDecimal.valueOf(discountRateHours)));
        switch (kind) {
            case VISITOR:
                return visitorRate.calculate(rate);
            case STUDENT:
                return studentRate.calculate(rate);
            case STAFF:
                return staffRate.calculate(rate);
            default: //MANAGEMENT
                return managementRate.calculate(rate);
        }
    }
}