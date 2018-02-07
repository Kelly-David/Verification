import java.math.BigDecimal;
import java.util.ArrayList;


public class Rate {

    public enum CarParkKind {STAFF, STUDENT, MANAGEMNET, VISITOR};

    CarParkKind kind;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) {

        // Check Kind
        this.kind = kind;

        // Normal Rate
        if((normalRate.longValue() <= 0) || (normalRate.longValue() < discountedRate.longValue())) {
            throw new IllegalArgumentException();
        }

        // Discounted Rate



    }

}

