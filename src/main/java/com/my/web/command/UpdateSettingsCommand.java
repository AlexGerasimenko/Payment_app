package com.my.web.command;

import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import com.my.exception.DBException;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * View settings command.
 *
 * @author
 */
public class UpdateSettingsCommand extends Command {


    private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);
    private static final long serialVersionUID = -713509484876500715L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

            return Path.PAGE_ERROR_PAGE;

        }
        String newLocale = request.getParameter("locale");
        user.setLocale(newLocale);
        DBManager.getInstance().updateUser(user, newLocale);

        request.getSession().setAttribute("currentLocale", newLocale);

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }

}