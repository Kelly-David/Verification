public class Period {
    int startHour;
    int endHour;

    public Period(int start, int end) {
        this.startHour = start;
        this.endHour = end;
    }

    int duration() {
        return this.endHour - this.startHour;
    };

    boolean overlaps() {
        return true;
    }
}
