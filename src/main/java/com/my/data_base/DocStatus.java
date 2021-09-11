package com.my.data_base;


import com.my.data_base.entity.Payment;

/**
 * Status entity.
 *
 * @author
 */
public enum DocStatus {
    PREPARED, SENDED;

    public static DocStatus getStatus(Payment payment) {
        int statusId = payment.getStatusId();
        return DocStatus.values()[statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}