package com.my.web.command;

import org.apache.log4j.Logger;
import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.Job;
import com.my.data_base.UserStatus;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import com.my.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login command.
 *
 * @author
 */
public class RegistrationCommand extends Command {

    private static final long serialVersionUID = -8022911451862589163L;

    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    private static final String LOCALE_BY_DEFAULT = "en";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        // obtain login and password from a request
        DBManager manager = DBManager.getInstance();
        String forward = "";
        Job job = (Job) request.getSession().getAttribute("userRole");
        if (job == null || Job.CLIENT.equals(job)) {
            return registerNewUser(request, response, false);
        }

        return registerNewUser(request, response, true);
    }

    public static String registerNewUser(HttpServletRequest request, HttpServletResponse response, boolean byAdmin)
            throws IOException, ServletException, AppException {

        LOG.debug("Command starts");
        // obtain login and password from a request
        DBManager manager = DBManager.getInstance();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        LOG.trace("Request parameter: firstName --> " + firstName);
        String lastName = request.getParameter("last_name");
        LOG.trace("Request parameter: lastName --> " + lastName);

        String currLocale = LOCALE_BY_DEFAULT;
        if (byAdmin) {
            User sysUser = (User) request.getSession().getAttribute("user");
            currLocale = sysUser.getLocale();
        }


        if (!testField(login) || !testField(password)
                || !testField(firstName) || !testField(lastName)) {
            throw new AppException("Filling fields cannot be empty.");
        }


        User user = manager.findUserByLogin(login, currLocale);
        LOG.trace("Found in DB: user --> " + user);

        if (user != null) {
            throw new AppException("Such user already register in system");
        }

        //create user

        user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setLocale(LOCALE_BY_DEFAULT);

        if (byAdmin) {

            String status = request.getParameter("status");
            String role = request.getParameter("role");

            user.setStatusId(UserStatus.valueOf(status).getId());
            user.setJobId(Job.valueOf(role).getId());

        } else {

            user.setStatusId(UserStatus.NEW.getId());
            user.setJobId(Job.CLIENT.getId());
            ;

        }


        String forward = Path.PAGE_ERROR_PAGE;

        if (manager.createUser(user, currLocale)) {

            forward = Path.PAGE_INFORMATION_PAGE;
            LOG.trace("new user created in db --> " + user.getLogin());
            request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_CONGRATULATION, currLocale));
            if (byAdmin) {
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_SUCCESSFULLY_ADDED, currLocale));
            } else {
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_SUCCESSFULLY_CREATED_AND_WAITING_MODERATION, currLocale));
            }

        }

        LOG.debug("Command finished");
        return forward;

    }

    public static boolean testField(String field) {

        if (field == null || field.isEmpty()) {
            return false;
        }

        return true;

    }

}