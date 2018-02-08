import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.*;
import java.util.function.*;


public class Rate {

    // public enum CarParkKind {STAFF, STUDENT, MANAGEMENT, VISITOR};

    CarParkKind kind;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) {

        // Kind
        this.kind = kind;

        //
        if(normalRate == null || discountedRate == null) {
            throw new IllegalArgumentException();
        }

        // Normal Rate
        if((normalRate.longValue() <= 0) || (normalRate.longValue() <= discountedRate.longValue())) {
            throw new IllegalArgumentException();
        }

        // Discounted Rate
        if((discountedRate.longValue() <= 0) || (discountedRate.longValue() >= normalRate.longValue())) {
            throw new IllegalArgumentException();
        }

        // Discounted Rate
        if((discountedRate.longValue() <= 0) || (discountedRate.longValue() >= normalRate.longValue())) {
            throw new IllegalArgumentException();
        }

        // Discount Periods
        discountPeriods.forEach(period -> {
            if(period.startHour > period.endHour) {
                throw new IllegalArgumentException();
            }
            if(period.duration() > 23) {
                throw new IllegalArgumentException();
            }
        });

        normalPeriods.forEach(period -> {
            if(period.startHour > period.endHour) {
                throw new IllegalArgumentException();
            }
            if(period.duration() > 23) {
                throw new IllegalArgumentException();
            }
        });
    }

    public BigDecimal calculateCharge(Period periodStay) {

        return new BigDecimal(0);
    }

}

