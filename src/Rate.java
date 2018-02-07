import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.*;
import java.util.function.*;


public class Rate {

    public enum CarParkKind {STAFF, STUDENT, MANAGEMENT, VISITOR};

    CarParkKind kind;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) {

        // Kind
        this.kind = kind;

        // Normal Rate
        if((normalRate.longValue() <= 0) || (normalRate.intValue() <= discountedRate.intValue())) {
            throw new IllegalArgumentException();
        }

        // Discounted Rate
        if((discountedRate.longValue() <= 0) || (discountedRate.intValue() >= normalRate.intValue())) {
            throw new IllegalArgumentException();
        }

        // Discount Periods
        for(Period p : discountPeriods) {
            if(p.startHour >= p.endHour) {
                throw new IllegalArgumentException();
            }
        }


    }

}

