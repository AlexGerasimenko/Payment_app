package com.my.web.command;

import org.apache.log4j.Logger;
import com.my.Path;
import com.my.data_base.Bool;
import com.my.data_base.DBManager;
import com.my.data_base.bean.UserCountClientChangeBean;
import com.my.data_base.entity.Count;
import com.my.data_base.entity.UnlockRequest;
import com.my.data_base.entity.User;
import com.my.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Lists menu items.
 *
 * @author
 */
public class EditCountsByClientCommand extends Command {

    private static final long serialVersionUID = 6672871317091711295L;

    private static final Logger LOG = Logger.getLogger(EditCountsByClientCommand.class);

    private static final String ACTION = "editUserCounts";
    private static final String END_EDIT = "endEdit";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");
        String setUnlockRequest = request.getParameter("setUnlockRequest");
        DBManager manager = DBManager.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        String forward = Path.PAGE_ERROR_PAGE;

        if (setUnlockRequest != null) {
            Count count = manager.findCount(Integer.valueOf(setUnlockRequest), user.getLocale());
            LOG.debug("Set unblocked request for count" + count);
            if (Bool.YES.getId() == count.getIsBlockedId()) {
                UnlockRequest unlockRequest = manager.findUnlockRequest(count.getId(), user.getLocale());
                if (unlockRequest == null) {
                    unlockRequest = new UnlockRequest();
                    unlockRequest.setCountId(count.getId());
                    unlockRequest.setActual(Bool.YES.getId());
                    if (!manager.createUnlockRequest(unlockRequest, user.getLocale())) {
                        return forward;
                    }
                } else {
                    unlockRequest.setActual(Bool.YES.getId());
                    if (!manager.updateCount(count, unlockRequest, user.getLocale())) {
                        return forward;
                    }
                }
            }
        }


        List<UserCountClientChangeBean> userCountList = manager.getUserCountClientList(user.getId(), user.getLocale());
        request.setAttribute("countList", userCountList);
        request.setAttribute("pageAction", ACTION);

        forward = Path.PAGE_EDIT_CLIENT_DATA_PAGE;
        LOG.debug("Command finished");
        return forward;
    }

}