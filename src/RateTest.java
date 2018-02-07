import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by davidkelly on 07/02/2018.
 */

public class RateTest {

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThan0() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.VISITOR, BigDecimal.valueOf(-1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateGreaterThanZero() {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateEqualOne() {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualZero() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(0), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(7), discountPeriods, normalPeriods);
    }

    @org.junit.Test
    public void normalRateGreaterThanDiscountRate() {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(4), discountPeriods, normalPeriods);
    }
}