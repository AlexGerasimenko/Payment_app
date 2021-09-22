package com.my.web.command;

import com.my.Path;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command.
 *
 * @author
 */
public class LogoutCommand extends Command {


    private static final long serialVersionUID = 3617139672409985638L;
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BasicConfigurator.configure();
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }

}