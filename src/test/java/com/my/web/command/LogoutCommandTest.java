package com.my.web.command;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class LogoutCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    LogoutCommand logoutCommand;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        logoutCommand = new LogoutCommand();
    }

    @Test
    public void execute() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);

        Assert.assertEquals("/login.jsp", logoutCommand.execute(request, response));
        Mockito.verify(request, times(1)).getSession(false);
        Mockito.verify(session, times(1)).invalidate();

    }

    @After
    public void tearDown() {
        request = null;
        response = null;
        logoutCommand = null;
    }
}