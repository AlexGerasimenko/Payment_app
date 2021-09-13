package com.my.web.command;

import org.apache.log4j.Logger;
import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.UserStatus;
import com.my.data_base.bean.UserAdminChangeBean;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import com.my.exception.Messages;

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
public class ChangeStatusCommand extends Command {

    private static final long serialVersionUID = -2551781626803738431L;

    private static final Logger LOG = Logger.getLogger(ChangeStatusCommand.class);


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        List<UserAdminChangeBean> usersBean = DBManager.getInstance().getNewAndBlockedUsers(user.getLocale());
        Map<Integer, Integer> param = new HashMap();
        String atr;
        for (UserAdminChangeBean bean : usersBean) {

            atr = (String) request.getParameter(Integer.toString(bean.getUserId()));
            if (atr != null) {
                param.put(bean.getUserId(), UserStatus.valueOf(atr).getId());
            }

        }

        String forward = Path.PAGE_ERROR_PAGE;

        if (DBManager.getInstance().updateUserStatus(param, user.getLocale())) {

            forward = Path.PAGE_INFORMATION_PAGE;
            LOG.trace("Statuses of users was successful changed in db --> " + param);
            request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_CONGRATULATION, user.getLocale()));
            request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_STATUSES_USERS_SUCCESSFULLY_CHANGED, user.getLocale()));

        }

        LOG.debug("Command finished");
        return forward;
    }

}