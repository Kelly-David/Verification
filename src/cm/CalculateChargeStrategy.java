package cm;

import java.math.BigDecimal;

interface CalculateChargeStrategy {

    BigDecimal calculate(BigDecimal rate);
}

class VisitorRate implements CalculateChargeStrategy {

    private BigDecimal visitorRateNoCharge = BigDecimal.valueOf(10);
    private BigDecimal visitorRateDiscount = BigDecimal.valueOf(0.5);

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        rate = (visitorRateDiscount.multiply(rate.subtract(visitorRateNoCharge)));
        return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
    }
}

class StudentRate implements CalculateChargeStrategy {

    private BigDecimal studentBaseLine = BigDecimal.valueOf(5);
    private BigDecimal studentRateDiscount = BigDecimal.valueOf(0.3);

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        if (rate.compareTo(BigDecimal.valueOf(5.00)) > 0) {
            rate = studentBaseLine.add(((rate)
                    .subtract(studentBaseLine))
                    .subtract(((rate)
                            .subtract(studentBaseLine))
                            .multiply(studentRateDiscount)));
        }
        return (rate.compareTo(BigDecimal.ZERO) > 0) ? rate : BigDecimal.ZERO;
    }
}

class StaffRate implements CalculateChargeStrategy {

    private BigDecimal staffRateMaxCharge = BigDecimal.valueOf(15);

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return (rate.compareTo(BigDecimal.valueOf(15)) <= 0) ? rate : staffRateMaxCharge;
    }
}

class ManagementRate implements CalculateChargeStrategy {

    @Override
    public BigDecimal calculate(BigDecimal rate) {
        return rate;
    }
}