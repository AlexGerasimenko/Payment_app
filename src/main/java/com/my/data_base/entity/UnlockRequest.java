package com.my.data_base.entity;

/**
 * Category entity.
 *
 * @author
 */
public class UnlockRequest extends Entity {


    private int countId;
    private int actual;
    private String date;


    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UnlockRequest [countId=" + countId
                + " actual=" + actual + "]";
    }

}
