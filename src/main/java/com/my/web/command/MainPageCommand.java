package com.my.web.command;

import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.Role;
import com.my.data_base.PaymentStatus;
import com.my.data_base.UserStatus;
import com.my.data_base.bean.CountPaymentBean;
import com.my.data_base.bean.UserAdminChangeBean;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lists menu items.
 *
 * @author
 */
public class MainPageCommand extends Command {

    private static final long serialVersionUID = 7565607814303480921L;

    private static final Logger LOG = Logger.getLogger(MainPageCommand.class);
    private static final String SORT_BY_DATE_ASC = "byDateAsc";
    private static final String SORT_BY_DATE_DESC = "byDateDesc";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");

        Role role = (Role) request.getSession().getAttribute("userRole");
        User user = (User) request.getSession().getAttribute("user");

        String forward = Path.PAGE_LOGIN;

        if (role == Role.ADMIN) {

            forward = Path.PAGE_MAIN_ADMIN_PAGE;
            List<UserAdminChangeBean> usersBean = DBManager.getInstance().getNewAndBlockedUsers(user.getLocale());
            LOG.trace("Found in DB: new and blocked users --> " + usersBean);
            Collections.sort(usersBean, new Comparator<UserAdminChangeBean>() {
                public int compare(UserAdminChangeBean o1, UserAdminChangeBean o2) {
                    return o1.getUserId() - o2.getUserId();
                }
            });

            request.setAttribute("blockedUsers", usersBean);
            List<String> statusList = new ArrayList<String>();

            for (UserStatus value : UserStatus.values()) {

                statusList.add(value.name());

            }
            request.setAttribute("statusList", statusList);

            LOG.trace("Set the request attribute: menuItems --> " + usersBean);

        } else if (role == Role.CLIENT) {

            forward = Path.PAGE_MAIN_CLIENT_PAGE;
            String sortBy = request.getParameter("sortBy");

            String orderPayments = (String) request.getSession().getAttribute("orderPayments");


            String paymentsSortBy;
            if (sortBy == null) {
                if (orderPayments == null) {

                    paymentsSortBy = "number";

                } else {

                    paymentsSortBy = orderPayments;

                }
            } else {

                paymentsSortBy = sortBy;


            }

            request.getSession().setAttribute("orderPayments", paymentsSortBy);

            List<CountPaymentBean> countPaymentBeanList = DBManager.getInstance().getCountPaymentBeanList(user.getId(), user.getLocale());

            //дописать сортировку

            LOG.trace("Found in DB: payment list --> " + countPaymentBeanList);
            switch (paymentsSortBy) {
                case SORT_BY_DATE_ASC:
                    Collections.sort(countPaymentBeanList, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
                    break;
                case SORT_BY_DATE_DESC:
                    Collections.sort(countPaymentBeanList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                    break;
                default:
                    Collections.sort(countPaymentBeanList, (o1, o2) -> o1.getId() - o2.getId());
            }

            request.setAttribute("countPaymentBeanList", countPaymentBeanList);
            request.setAttribute("statusList", PaymentStatus.getMap());

            LOG.trace("Set the request attribute: paymentList --> " + countPaymentBeanList);

        }

        LOG.debug("Command finished");
        return forward;
    }

}