package com.my.data_base.bean;

import com.my.data_base.entity.Entity;

/**
 * Provide records for virtual table:
 *
 * @author
 */
public class UserCountAdminChangeBean extends Entity {

    private static final long serialVersionUID = -6803562448500960948L;

    private int userId;

    private String count;

    private String isBlocked;

    private String unblockedRequest;

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

    @Override
    public String toString() {
        return "CountsUserBean [userId=" + userId + ", count="
                + count
                + ", isBlocked=" + isBlocked
                + ", unblockedRequest=" + unblockedRequest
                + "]";
    }
}
