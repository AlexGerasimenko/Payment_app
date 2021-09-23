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
        item.setId(expected);
        assertEquals(expected, item.getId());
    }

    @Test
    public void getCount() {
        int expected = 2;
        item.setId(expected);
        assertEquals(expected, item.getId());
    }

    @Test
    public void getIsBlockedId() {
        int expected = 0;
        item.setId(expected);
        assertEquals(expected, item.getId());
    }
}