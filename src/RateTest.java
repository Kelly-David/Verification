import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by meudecc on 15/02/2017.
 */

public class RateTest {
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void kindValid() throws Exception {
        ArrayList<Period> discountPeriods = new ArrayList();
        ArrayList<Period> normalPeriods = new ArrayList();
        Rate rt = new Rate(Rate.CarParkKind.STAFF, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }
}