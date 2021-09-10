package com.my.web.command;

import org.apache.log4j.Logger;
import com.my.Path;
import com.my.data_base.Role;
import com.my.data_base.UserStatus;
import com.my.exception.AppException;
import com.my.web.command.RegistrationCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Lists menu items.
 *
 * @author
 */
public class AddUserCommand extends Command {

    private static final long serialVersionUID = 633739808525562864L;

    private static final Logger LOG = Logger.getLogger(AddUserCommand.class);

    private static final String ACTION = "newUser";
    private static final String END_ADD = "endAdd";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String stage = request.getParameter("stage");
        String forward = "";
        if (stage != null & END_ADD.equals(stage)) {
            forward = endAddUser(request, response);
        } else {
            forward = beginAddUser(request, response);
        }
        LOG.debug("Command finished");
        return forward;


    }

    private String beginAddUser(HttpServletRequest request, HttpServletResponse response) {

        Map<Integer, String> roleMap = Role.getMap();
        Map<Integer, String> statusMap = UserStatus.getMap();

        request.setAttribute("roleMap", roleMap.entrySet());
        request.setAttribute("statusMap", statusMap.entrySet());
        request.setAttribute("pageAction", ACTION);

        LOG.trace("Set the request attribute: statusMap --> " + statusMap);
        LOG.trace("Set the request attribute: roleMap --> " + roleMap);

        LOG.debug("Command finished");
        return Path.PAGE_EDIT_USER_ADMIN_PAGE;

    }

    private String endAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        return RegistrationCommand.registerNewUser(request, response, true);

    }

}