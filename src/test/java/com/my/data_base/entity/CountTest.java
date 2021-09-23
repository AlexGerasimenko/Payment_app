package com.my.data_base.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountTest {
    private Count item;

    @Before
    public void setUp() throws Exception {
        item = new Count();
    }

    @Test
    public void getUserId() {
        int expected = 1;
        item.setUserId(expected);
        assertEquals(expected, item.getUserId());
    }

    @Test
    public void getCount() {
        String expected = "1";
        item.setCount(expected);
        assertEquals(expected, item.getCount());
    }

    @Test
    public void getIsBlockedId() {
        int expected = 0;
        item.setIsBlockedId(expected);
        assertEquals(expected, item.getIsBlockedId());
    }
}