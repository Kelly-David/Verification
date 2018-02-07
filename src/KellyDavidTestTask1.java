import org.junit.Before;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by davidkelly on 07/02/2018.
 */

public class KellyDavidTestTask1 {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> normalPeriods;

    @Before
    public void instantiatePeriods() {
        discountPeriods = new ArrayList<>();
        discountPeriods.add(new Period(6,7));
        normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2,5));
    }

    /*
    TEST 1: Kind is valid.
     */
    @org.junit.Test
    public void kindIsValid() {
        Rate rt = new Rate(Rate.CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 2: normalRate < 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThan0() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STAFF, BigDecimal.valueOf(-1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 3: normal rate > 0
     */
    @org.junit.Test
    public void normalRateGreaterThanZero() {
        Rate rt = new Rate(Rate.CarParkKind.MANAGEMENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
    TEST 4: normalRate == 1
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualOne() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    } // Throws exception as discount !< normal.

    /*
    TEST 4: normalRate == 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualZero() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(0), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    /*
    TEST 5: normalRate == maxInt
     */
    @org.junit.Test
    public void normalRateMaxInt() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(42), discountPeriods, normalPeriods);
    }

    /*
    TEST 6: normalRate < discountRate
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(7), discountPeriods, normalPeriods);
    }

    /*
    TEST 7: normalRate > discountRate
     */
    @org.junit.Test
    public void normalRateGreaterThanDiscountRate() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(4), discountPeriods, normalPeriods);
    }

    /*
    TEST 8: normalRate, discountRate invalid argument
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateInvalidArgument() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf('J'), BigDecimal.valueOf('f'), discountPeriods, normalPeriods);
    }

    /*
    TEST 8: discountRate < 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateLessThan0() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(-1), discountPeriods, normalPeriods);
    }

    /*
    TEST 9: discountRate == 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateEqualZero() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    /*
    TEST 10: discountRate > normalRate
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateGreaterThanNormalRate() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 11: discountRate == 1
     */
    @org.junit.Test
    public void discountRateEqualToOne() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
    TEST 12: discountRate == maxInt - 1
     */
    @org.junit.Test
    public void discountRateMaxInt() {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(Integer.MAX_VALUE -1 ), discountPeriods, normalPeriods);
    }

    /*
    TEST 13: discountRate == normalRate
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateEqualToNormal() throws Exception {
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 14: discountPeriod start > discountPeriod end
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodStartGreaterThanEnd() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(10, 1));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 15: discountPeriod min period
     */
    @org.junit.Test
    public void discountPeriodMin() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 16: discountPeriod max
     */
    @org.junit.Test
    public void discountPeriodMax() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 23));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 17: discountPeriod out of bounds
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOutOfBounds() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 48));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 18: discountPeriod out of bounds (boundary check)
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOutOfBoundsOnBoundary() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 19: discountPeriod within bounds
     */
    @org.junit.Test
    public void discountPeriodValidPeriod() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(11, 17));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 20: discountPeriod overlap
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlap() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(2, 4)); add(new Period(3, 6));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 21: discountPeriod no period specified
     */
    @org.junit.Test
    public void discountPeriodNoPeriodSpecified() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 22: discountPeriod overlap with normalPeriod
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlapWithNormal() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(2, 7));}};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(6, 10));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 23: discountPeriod overlap with normalPeriod
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlapWithNormalAlt() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(2, 7)); add(new Period(10, 23));}};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(7, 10)); add(new Period(10, 11));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 24: normalPeriod start > normalPeriod end
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodStartGreaterThanEnd() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(10, 1));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 25: normalPeriod min period
     */
    @org.junit.Test
    public void normalPeriodMin() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 26: normalPeriod max
     */
    @org.junit.Test
    public void normalPeriodMax() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 23));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 27: normalPeriod out of bounds
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOutOfBounds() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 48));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 28: normalPeriod out of bounds (boundary check)
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOutOfBoundsOnBoundary() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 29: normalPeriod within bounds
     */
    @org.junit.Test
    public void normalPeriodValidPeriod() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(11, 17));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 30: normalPeriod overlap
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOverlap() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(2, 4)); add(new Period(3, 6));}};
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

}
