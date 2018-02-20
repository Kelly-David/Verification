//package cm;

import cm.CarParkKind;
import cm.Period;
import cm.Rate;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by davidkelly on 07/02/2018.
 */

public class KellyDavidTestTask3 {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> discountPeriodsCC1;
    ArrayList<Period> discountPeriodsCC2;
    ArrayList<Period> discountPeriodsCC3;

    ArrayList<Period> normalPeriods;
    ArrayList<Period> normalPeriodsCC1;
    ArrayList<Period> normalPeriodsCC2;
    ArrayList<Period> normalPeriodsCC3;

    ArrayList<Period> normalPeriodsCC4;
    ArrayList<Period> discountPeriodsCC4;

    BigDecimal b;


    @Before
    public void instantiatePeriods() {
        discountPeriods = new ArrayList<>();
        discountPeriods.add(new Period(6,7));
        normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2,5));
        normalPeriods.add(new Period(17,20));

        // ArrayList
        discountPeriodsCC1 = new ArrayList<Period>() {{ add(new Period(2,4)); add(new Period(18,19)); }};
        normalPeriodsCC1 = new ArrayList<Period>() {{ add(new Period(0,2)); add(new Period(13,17)); add(new Period(23,24)); }};

        // ArrayList
        discountPeriodsCC2 = new ArrayList<Period>() {{ add(new Period(0,4)); add(new Period(23,24)); }};
        normalPeriodsCC2 = new ArrayList<Period>() {{ add(new Period(6,23)); }};

        // ArrayList
        discountPeriodsCC3 = new ArrayList<Period>() {{ add(new Period(0,2)); add(new Period(13,17)); add(new Period(23,24)); }};
        normalPeriodsCC3 = new ArrayList<Period>() {{ add(new Period(2,4)); add(new Period(5,12)); add(new Period(17,19)); }};

        // ArrayList (null pointers)
        normalPeriodsCC4 = null;
        discountPeriodsCC4 = null;

        b = null;
    }

    /*
    ----------------------------------------------- rate(...) Unit Tests -----------------------------------------------
     */

    /*
    TEST 1: Kind is valid.
     */
    @org.junit.Test
    public void kindIsValid() {
        Rate rt = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 2: normal rate > 0
     */
    @org.junit.Test
    public void normalRateGreaterThanZero() {
        Rate rt = new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(2.5), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
    TEST 3: normalRate == maxInt
     */
    @org.junit.Test
    public void normalRateMaxInt() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(42), discountPeriods, normalPeriods);
    }

    /*
    TEST 4: normalRate > discountRate
     */
    @org.junit.Test
    public void normalRateGreaterThanDiscountRate() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(4), discountPeriods, normalPeriods);
    }

    /*
    TEST 5: normalRate < 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThan0() throws Exception {
        Rate rt = new Rate(CarParkKind.STAFF, BigDecimal.valueOf(-1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 6: normalRate == 1
     */
    @org.junit.Test
    public void normalRateEqualOne() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(.5), discountPeriods, normalPeriods);
    }

    /*
    TEST 7: normalRate == 0
     */
    @org.junit.Test
    public void normalRateEqualZero() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(0), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    /*
    TEST 8: normalRate, discountRate invalid argument
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateInvalidArgument() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf('J'), BigDecimal.valueOf('f'), discountPeriods, normalPeriods);
    }

    /*
    TEST 9: normalRate < discountRate
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(7), discountPeriods, normalPeriods);
    }

    /*
    TEST 10: discountRate > normalRate
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateGreaterThanNormalRate() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 11: discountRate == 1
     */
    @org.junit.Test
    public void discountRateEqualToOne() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1.57), discountPeriods, normalPeriods);
    }

    /*
    TEST 12: discountRate == maxInt - 1
     */
    @org.junit.Test
    public void discountRateMaxInt() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(Integer.MAX_VALUE -1 ), discountPeriods, normalPeriods);
    }

    /*
    TEST 13: discountRate < 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateLessThan0() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(-1), discountPeriods, normalPeriods);
    }

    /*
    TEST 14: discountRate == 0
     */
    @org.junit.Test
    public void discountRateEqualZero() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    /*
    TEST 15: discountRate == normalRate
     */
    @org.junit.Test
    public void discountRateEqualToNormal() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 16: discountPeriod min period
     */
    @org.junit.Test
    public void discountPeriodMin() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 17: discountPeriod max
     */
    @org.junit.Test
    public void discountPeriodMax() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 18: discountPeriod within bounds
     */
    @org.junit.Test
    public void discountPeriodValidPeriod() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(11, 17));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 19: discountPeriod no period specified
     */
    @org.junit.Test
    public void discountPeriodNoPeriodSpecified() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 20: discountPeriods max number periods
     */
    @org.junit.Test
    public void discountPeriodsMaxPeriods() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            for (int i = 0; i < 24; i++) {
                add(new Period(i, i + 1));
            }
        }};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 21: discountPeriod start > discountPeriod end
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodStartGreaterThanEnd() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(10, 1));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 22: discountPeriod out of bounds
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOutOfBounds() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 48));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 23: discountPeriod out of bounds (boundary check)
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOutOfBoundsOnBoundary() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 25));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 24: discountPeriod overlap
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlap() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(6,7)); add(new Period(6,8));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 25: discountPeriod overlap with normalPeriod
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlapWithNormal() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(2, 7));}};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(6, 10));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 26: discountPeriod overlap with normalPeriod
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodOverlapWithNormalAlt() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(2, 7)); add(new Period(10, 23));}};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(7, 10)); add(new Period(10, 11));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 27: discountPeriods too many periods
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodsTooManyPeriods() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{
            for (int i = 0; i < 25; i++) {
                add(new Period(i, i + 1));
            }
        }};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 28: normalPeriod min range period
     */
    @org.junit.Test
    public void normalPeriodMin() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 29: normalPeriod max range period
     */
    @org.junit.Test
    public void normalPeriodMax() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 30: normalPeriod within bounds
     */
    @org.junit.Test
    public void normalPeriodValidPeriod() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{ add(new Period(11, 17));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 31: normalPeriods max number periods
     */
    @org.junit.Test
    public void normalPeriodsMaxPeriods() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            for (int i = 0; i < 24; i++) {
                add(new Period(i, i + 1));
            }
        }};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 32: normalPeriod start > normalPeriod end
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodStartGreaterThanEnd() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(10, 1));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 33: normalPeriod out of bounds
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOutOfBounds() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 48));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 34: normalPeriod out of bounds (boundary check)
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOutOfBoundsOnBoundary() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 25));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 35: normalPeriod overlap
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOverlap() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(2, 4)); add(new Period(3, 5));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 36: normalPeriods too many periods
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodsTooManyPeriods() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{
            for (int i = 0; i < 25; i++) {
                add(new Period(i, i + 1));
            }
        }};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 37: only free periods
     */
    @org.junit.Test
    public void onlyfreePeriod() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 38: normalPeriods null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodsNullPointer() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriodsCC4);
    }

    /*
    TEST 39: discountPeriods null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodsNullPointer() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC4, normalPeriods);
    }

    /*
    TEST 40: normal periods and discount periods null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void periodsNullPointer() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC4, normalPeriodsCC4);
    }

    /*
    TEST 41: normalRate is null
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateNull() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, b, BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 42: discountRate is null
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateNull() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), b, discountPeriods, normalPeriods);
    }

    /*
    TEST 43: normalRate and discountRate is null
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ratesNull() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, b, b, discountPeriods, normalPeriods);
    }

    /*
    TEST 44: discountPeriods contain null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountPeriodsContainNull() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(null);}};
        Rate rt = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 45: normalPeriods contain null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodsContainNull() throws Exception {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(null);}};
        Rate rt = new Rate(CarParkKind.STAFF, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 46: normalPeriods and discountPeriods contain null pointer
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void periodsListsContainNull() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(null);}};
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(null);}};
        Rate rt = new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    ------------------------------------------- calculate() Unit Tests -------------------------------------------
     */

    /*
    TEST 1: first period, normal period boundary
     */
    @org.junit.Test
    public void firstPeriodNormalPeriodBound() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC1, normalPeriodsCC1);
        Period periodStay = new Period(0,1);
        assertEquals(BigDecimal.valueOf(3), rt.calculate(periodStay));

    }

    /*
    TEST 2: last period, normal period boundary
     */
    @org.junit.Test
    public void lastPeriodNormalPeriodBound() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC1, normalPeriodsCC1);
        Period periodStay = new Period(23,24);
        assertEquals(BigDecimal.valueOf(3), rt.calculate(periodStay));

    }

    /*
    TEST 3: first period, discount period boundary
     */
    @org.junit.Test
    public void firstPeriodDiscountPeriodBound() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC2, normalPeriodsCC2);
        Period periodStay = new Period(0,1);
        assertEquals(BigDecimal.valueOf(2), rt.calculate(periodStay));

    }

    /*
    TEST 4: last period, discount period boundary
     */
    @org.junit.Test
    public void lastPeriodDiscountPeriodBound() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC2, normalPeriodsCC2);
        Period periodStay = new Period(23,24);
        assertEquals(BigDecimal.valueOf(2), rt.calculate(periodStay));

    }

    /*
    TEST 5: arbitrary period - normal rate
     */
    @org.junit.Test
    public void arbitraryPeriodNormalRate() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC2, normalPeriodsCC2);
        Period periodStay = new Period(14,17);
        assertEquals(BigDecimal.valueOf(9), rt.calculate(periodStay));
    }

    /*
    TEST 6: first period in normal period
     */
    @org.junit.Test
    public void firstNormalPeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(4), BigDecimal.valueOf(1), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(2,4);
        assertEquals(BigDecimal.valueOf(8), rt.calculate(periodStay));
    }

    /*
    TEST 7: last period in normal period
     */
    @org.junit.Test
    public void lastNormalPeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(4), BigDecimal.valueOf(1), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(17,19);
        assertEquals(BigDecimal.valueOf(8), rt.calculate(periodStay));
    }

    /*
    TEST 8: arbitrary period - discount rate
     */
    @org.junit.Test
    public void arbitraryPeriodDiscountRate() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(4), BigDecimal.valueOf(1), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(14,17);
        assertEquals(BigDecimal.valueOf(3), rt.calculate(periodStay));
    }

    /*
    TEST 9: normal period and free period
     */
    @org.junit.Test
    public void normalPeriodAndFreePeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(18,20);
        assertEquals(BigDecimal.valueOf(3), rt.calculate(periodStay));
    }

    /*
    TEST 10: normal period and discount period
     */
    @org.junit.Test
    public void normalPeriodAndDiscountPeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(1,3);
        assertEquals(BigDecimal.valueOf(5), rt.calculate(periodStay));
    }

    /*
    TEST 11: discount period and free period
     */
    @org.junit.Test
    public void discountPeriodAndFreePeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(12,14);
        assertEquals(BigDecimal.valueOf(2), rt.calculate(periodStay));
    }

    /*
    TEST 12: normal, discount and free period
     */
    @org.junit.Test
    public void normalDiscountFreePeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(16,20);
        assertEquals(BigDecimal.valueOf(8), rt.calculate(periodStay));
    }

    /*
    TEST 13: Free period (no charge)
     */
    @org.junit.Test
    public void freePeriod() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriodsCC3, normalPeriodsCC3);
        Period periodStay = new Period(20,23);
        assertEquals(BigDecimal.valueOf(0), rt.calculate(periodStay));
    }

    /*
    TEST 14: Visitor Rate
     */
    @org.junit.Test
    public void visitorRate() {
        Rate rt = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
        Period periodStay = new Period(7,20);
        assertEquals(BigDecimal.valueOf(2.5), rt.calculate(periodStay));
    }

    /*
    TEST 15: Management Rate
     */
    @org.junit.Test
    public void managementRate() {
        Rate rt = new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
        Period periodStay = new Period(5,8);
        assertEquals(BigDecimal.valueOf(2), rt.calculate(periodStay));
    }

    /*
    TEST 16: Student Rate
     */
    @org.junit.Test
    public void studentRate() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
        Period periodStay = new Period(7,19);
        assertEquals(BigDecimal.valueOf(8.5), rt.calculate(periodStay));
    }

    /*
    TEST 17: Staff Rate
     */
    @org.junit.Test
    public void staffRate() {
        Rate rt = new Rate(CarParkKind.STAFF, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
        Period periodStay = new Period(4,19);
        assertEquals(BigDecimal.valueOf(15), rt.calculate(periodStay));
    }


}
