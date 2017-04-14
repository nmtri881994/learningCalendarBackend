package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 4/14/2017.
 */
public class TermWeekTime {
    private int startWeek;
    private int endWeek;

    public TermWeekTime() {
    }

    public TermWeekTime(int startWeek, int endWeek) {
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }
}
