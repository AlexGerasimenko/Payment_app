package com.my.web.command;

import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.Role;
import com.my.data_base.UserStatus;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = 8373001435089888590L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final String LOCALE_BY_DEFAULT = "en";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request
        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = manager.findUserByLogin(login, LOCALE_BY_DEFAULT);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = Role.getRole(user);
        UserStatus userStatus = UserStatus.getStatus(user);
        LOG.trace("userRole --> " + userRole);

        String forward;
        switch (userStatus) {
            case VALID:
                forward = Path.COMMAND_MAIN_CLIENT_PAGE;
                session.setAttribute("user", user);
                LOG.trace("Set the session attribute: user --> " + user);

                session.setAttribute("userRole", userRole);

                LOG.trace("Set the session attribute: userRole --> " + userRole);

                LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
                break;
            case NEW:

                forward = Path.PAGE_INFORMATION_PAGE;
                LOG.trace("new user try to login --> " + user.getLogin());

                request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_HEADER_INFORMATION, user.getLocale()));
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_WAITING_MODERATION, user.getLocale()));
                break;

            case BLOCKED:

                forward = Path.PAGE_INFORMATION_PAGE;
                LOG.trace("blocked user try to login --> " + user.getLogin());

                request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_HEADER_INFORMATION, user.getLocale()));
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_BLOCKED, user.getLocale()));
                break;
            default:
                LOG.trace("Cannot obtain status user: user --> " + user);
                throw new AppException("Cannot obtain status user.");
        }

        LOG.debug("Command finished");
        return forward;
    }

}