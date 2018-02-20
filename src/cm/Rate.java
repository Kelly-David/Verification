package cm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CM on 01/02/2018.
 */
public class Rate {
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyDiscountedRate;
    private ArrayList<Period> discount = new ArrayList<>();
    private ArrayList<Period> normal = new ArrayList<>();

    private BigDecimal visitorRateNoCharge = BigDecimal.valueOf(-10);
    private BigDecimal visitorRateDiscount = BigDecimal.valueOf(0.5);
    private BigDecimal studentBaseLine = BigDecimal.valueOf(5);
    private BigDecimal studentRateDiscount = BigDecimal.valueOf(0.3);
    private BigDecimal staffRateMaxCharge = BigDecimal.valueOf(15);


    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods
            , ArrayList<Period> normalPeriods) {
        if (discountPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || discountedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || discountedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(discountedRate) == -1) {
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
     * @return true if the period does not overlap in the collecton of periods
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
    public BigDecimal calculate(Period periodStay) {

        int normalRateHours = periodStay.occurences(normal);
        int discountRateHours = periodStay.occurences(discount);

        if (this.kind.equals(CarParkKind.VISITOR)) {
            BigDecimal rate = (
                    visitorRateDiscount.multiply((this.hourlyNormalRate
                            .multiply(BigDecimal.valueOf(normalRateHours)))
                            .add(this.hourlyDiscountedRate
                                    .multiply(BigDecimal.valueOf(discountRateHours)))
                            .add(visitorRateNoCharge)));

            return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
        }
        else if (this.kind.equals(CarParkKind.STUDENT)){

            BigDecimal rate =
                    studentBaseLine.add(((
                            (this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                                    .add(this.hourlyDiscountedRate
                                            .multiply(BigDecimal.valueOf(discountRateHours))))
                            .subtract(studentBaseLine))
                            .subtract((((this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                                    .add(this.hourlyDiscountedRate.multiply(BigDecimal.valueOf(discountRateHours))))
                                    .subtract(studentBaseLine))
                                    .multiply(studentRateDiscount)));

            return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
        }
        else if (this.kind.equals(CarParkKind.STAFF)) {

            BigDecimal rate = (
                    this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                    .add(this.hourlyDiscountedRate
                            .multiply(BigDecimal.valueOf(discountRateHours)));

            return (rate.compareTo(BigDecimal.valueOf(15)) <= 0) ? rate : staffRateMaxCharge;
        }

        return (
                this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                .add(this.hourlyDiscountedRate
                        .multiply(BigDecimal.valueOf(discountRateHours)));

    }

}
