import java.math.BigDecimal;
import java.util.ArrayList;


public class Rate {

    public enum CarParkKind {STAFF, STUDENT, MANAGEMENT, VISITOR};

    CarParkKind kind;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) {

        // Check Kind
        this.kind = kind;

        // Normal Rate
        if((normalRate.longValue() <= 0) || (normalRate.longValue() < discountedRate.longValue())) {
            throw new IllegalArgumentException();
        }

        // Discounted Rate
        if((discountedRate.longValue() <= 0) || (discountedRate.longValue() > normalRate.longValue())) {
            throw new IllegalArgumentException();
        }



    }

}

