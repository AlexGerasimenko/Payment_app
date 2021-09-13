package com.my.web.command;

import org.apache.log4j.Logger;
import com.my.Path;

import com.my.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Lists menu items.
 *
 * @author
 */
public class BeginEditUserCommand extends Command {

    private static final long serialVersionUID = -8084703981036832063L;

    private static final Logger LOG = Logger.getLogger(BeginEditUserCommand.class);

    private static final String ACTION = "beginEditUser";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        LOG.debug("Command starts");
        request.setAttribute("pageAction", ACTION);

        LOG.debug("Command finished");
        return Path.PAGE_EDIT_USER_ADMIN_PAGE;
    }

}