package cm;

import java.math.BigDecimal;

interface CalculateChargeStrategy {

    BigDecimal calculate(BigDecimal rate);
}

class VisitorBehaviour implements CalculateChargeStrategy {
    // First 10.00 if free
    private BigDecimal visitorRateNoCharge = BigDecimal.valueOf(10);
    // 50% discount on amount above 10.00
    private BigDecimal visitorRateDiscount = BigDecimal.valueOf(0.5);

    /**
     * Calculates charge for Visitor rate
     * @param rate the total rate for the period
     * @return BigDecimal adjusted rate
     */
    @Override
    public BigDecimal calculate(BigDecimal rate) {
        rate = (visitorRateDiscount.multiply(rate.subtract(visitorRateNoCharge)));
        return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
    }
}

class StudentBehaviour implements CalculateChargeStrategy {
    // First 5.00 not eligible for discount
    private BigDecimal studentBaseLine = BigDecimal.valueOf(5);
    // 30% discount on subsequent amount
    private BigDecimal studentRateDiscount = BigDecimal.valueOf(0.3);

    /**
     * Calculates charge for Student rate
     * @param rate the total rate for the period
     * @return BigDecimal adjusted rate
     */
    @Override
    public BigDecimal calculate(BigDecimal rate) {
        if (rate.compareTo(studentBaseLine) > 0) {
            rate = studentBaseLine.add(((rate)
                    .subtract(studentBaseLine))
                    .subtract(((rate)
                            .subtract(studentBaseLine))
                            .multiply(studentRateDiscount)));
        }
        return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
    }
}

class StaffBehaviour implements CalculateChargeStrategy {
    // Max payable is 15.00
    private BigDecimal staffRateMaxCharge = BigDecimal.valueOf(15);

    /**
     * Calculates charge for Staff rate
     * @param rate the total rate for the period
     * @return BigDecimal adjusted rate
     */
    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return (rate.compareTo(staffRateMaxCharge) <= 0) ? rate : staffRateMaxCharge;
    }
}

class ManagementBehaviour implements CalculateChargeStrategy {

    /**
     * Calculates charge for Management rate
     * @param rate the total rate for the period
     * @return BigDecimal adjusted rate
     */
    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return rate;
    }
}