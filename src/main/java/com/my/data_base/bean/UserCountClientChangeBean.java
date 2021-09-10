package com.my.data_base.bean;

import com.my.data_base.entity.Entity;

import java.util.List;

/**
 * Provide records for virtual table:
 *
 * @author
 */
public class UserCountClientChangeBean extends Entity {

    private static final long serialVersionUID = -2925282072854562406L;

    private int userId;

    private String count;

    private int countId;

    private String isBlocked;

    private String unblockedRequest;

    private int canUnblocked;

    private List<Integer> creditCardList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getUnblockedRequest() {
        return unblockedRequest;
    }

    public void setUnblockedRequest(String unblockedRequest) {
        this.unblockedRequest = unblockedRequest;
    }

    public int getCanUnblocked() {
        return canUnblocked;
    }

    public void setCanUnblocked(int canUnblocked) {
        this.canUnblocked = canUnblocked;
    }

    public List getCreditCardList() {
        return creditCardList;
    }

    public void setCreditCardList(List creditCardList) {
        this.creditCardList = creditCardList;
    }

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    @Override
    public String toString() {
        return "CountsUserBean [userId=" + userId + ", count="
                + count
                + "isBlocked=" + isBlocked
                + "unblockedRequest=" + unblockedRequest
                + "]";
    }
}
