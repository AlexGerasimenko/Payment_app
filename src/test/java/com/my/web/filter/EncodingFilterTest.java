package com.my.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class EncodingFilterTest {
    EncodingFilter filter;

    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() {
        filter = new EncodingFilter();
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        when(request.getCharacterEncoding()).thenReturn("UTF-8");

        filter.doFilter(request, response, chain);
        assertEquals("UTF-8", request.getCharacterEncoding());
    }
}