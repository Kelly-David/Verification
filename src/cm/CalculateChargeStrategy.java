package cm;

import java.math.BigDecimal;

interface CalculateChargeStrategy {

    BigDecimal calculate(BigDecimal rate);
}

class VisitorBehaviour implements CalculateChargeStrategy {

    private BigDecimal visitorRateNoCharge = BigDecimal.valueOf(10);
    private BigDecimal visitorRateDiscount = BigDecimal.valueOf(0.5);

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        rate = (visitorRateDiscount.multiply(rate.subtract(visitorRateNoCharge)));
        return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
    }
}

class StudentBehaviour implements CalculateChargeStrategy {

    private BigDecimal studentBaseLine = BigDecimal.valueOf(5);
    private BigDecimal studentRateDiscount = BigDecimal.valueOf(0.3);

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

    private BigDecimal staffRateMaxCharge = BigDecimal.valueOf(15);

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return (rate.compareTo(staffRateMaxCharge) <= 0) ? rate : staffRateMaxCharge;
    }
}

class ManagementBehaviour implements CalculateChargeStrategy {

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return rate;
    }
}