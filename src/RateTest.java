import org.junit.Before;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by davidkelly on 07/02/2018.
 */

public class RateTest {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> normalPeriods;

    @Before
    public void instantiatePeriods() {
        discountPeriods = new ArrayList<>();
        discountPeriods.add(new Period(6,7));
        normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2,5));
    }

    @org.junit.Test
    public void kindIsValid() {
        Rate rt = new Rate(Rate.CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThan0() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STAFF, BigDecimal.valueOf(-1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateGreaterThanZero() {
        Rate rt = new Rate(Rate.CarParkKind.MANAGEMENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateEqualOne() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualZero() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(0), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateMaxInt() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2147483647), BigDecimal.valueOf(42), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(7), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateGreaterThanDiscountRate() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(4), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateLessThan0() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(-1), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateEqualZero() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateGreaterThanNormalRate() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void discountRateEqualToOne() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void discountRateMaxInt() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2147483647), BigDecimal.valueOf(2147483647 - 1), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateEqualToDiscount() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }
}