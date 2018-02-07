public class Period {
    int startHour;
    int endHour;

    public Period(int start, int end) {
        this.startHour = start;
        this.endHour = end;
    }

    int duration() {
        return 0;
    };

    boolean overlaps() {
        return true;
    }
}
