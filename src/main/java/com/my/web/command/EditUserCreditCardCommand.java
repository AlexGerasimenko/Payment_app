package com.my.web.command;

import com.my.Path;
import com.my.data_base.DBManager;
import com.my.data_base.entity.CreditCard;
import com.my.data_base.entity.User;
import com.my.exception.AppException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Lists menu items.
 *
 * @author
 */
public class EditUserCreditCardCommand extends Command {

    private static final long serialVersionUID = -6780055733962452107L;

    private static final Logger LOG = Logger.getLogger(EditUserCreditCardCommand.class);

    private static final String ACTION = "editUserCreditCard";
    private static final String END_EDIT = "endEdit";
    private static final String ADD_CARD = "addCard";
    private static final String NAME_BUTTON_ADD = "button.add_credit_card";
    private static final String NAME_BUTTON_EDIT = "button.edit_credit_card";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String stage = request.getParameter("stage");
        String forward;
        if (stage != null & END_EDIT.equals(stage)) {
            forward = endEditUserCreditCards(request, response);
        } else {
            forward = beginEditUserCreditCards(request, response);
        }
        LOG.debug("Command finished");
        return forward;
    }

    private String beginEditUserCreditCards(HttpServletRequest request,
                                            HttpServletResponse response) throws AppException {


        String countId = request.getParameter("countId");
        String editCreditCard = request.getParameter("editCreditCard");
        User user = (User) request.getSession().getAttribute("user");

        String forward = Path.PAGE_ERROR_PAGE;

        Locale.setDefault(new Locale(user.getLocale()));
        ResourceBundle addCountBundle = ResourceBundle.getBundle("resources");

        DBManager manager = DBManager.getInstance();
        CreditCard creditCard;
        if (ADD_CARD.equals(editCreditCard)) {

            creditCard = new CreditCard();
            creditCard.setId(0);
            request.setAttribute("card", creditCard);
            request.setAttribute("countId", countId);
            request.setAttribute("pageAction", ACTION);
            request.setAttribute("nameButtonEdit", addCountBundle.getString(NAME_BUTTON_ADD));
            forward = Path.PAGE_EDIT_USER_ADMIN_PAGE;


        } else if (editCreditCard != null) {

            creditCard = manager.findCreditCard(Integer.valueOf(editCreditCard), user.getLocale());

            request.setAttribute("card", creditCard);
            request.setAttribute("countId", countId);
            request.setAttribute("pageAction", ACTION);
            request.setAttribute("nameButtonEdit", addCountBundle.getString(NAME_BUTTON_EDIT));
            forward = Path.PAGE_EDIT_USER_ADMIN_PAGE;

        }

        LOG.debug("Command finished");
        return forward;
    }

    private String endEditUserCreditCards(HttpServletRequest request,
                                          HttpServletResponse response) throws AppException {


        String editCreditCard = request.getParameter("editCreditCard");
        String count = request.getParameter("count");
        String expiration = request.getParameter("expiration");
        String cvv = request.getParameter("cvv");
        String countId = request.getParameter("countId");
        User user = (User) request.getSession().getAttribute("user");

        String forward = Path.PAGE_ERROR_PAGE;

        DBManager manager = DBManager.getInstance();

        if (editCreditCard != null && Integer.valueOf(editCreditCard) > 0) {

            CreditCard creditCard = manager.findCreditCard(Integer.valueOf(editCreditCard), user.getLocale());

            if (creditCard != null) {

                try {
                    creditCard.setNumber(Long.valueOf(count));
                    creditCard.setCvv(Integer.valueOf(cvv));
                    creditCard.setExpiration(expiration);
                } catch (Exception ex) {
                    throw new AppException("You enter incorrect data");
                }

            }


            if (manager.updateCreditCard(creditCard, user.getLocale())) {

                request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_CONGRATULATION, user.getLocale()));
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_CREDIT_CARD_SUCCESSFULLY_CHANGED, user.getLocale()));

                forward = Path.PAGE_INFORMATION_PAGE;


            }


        } else if (editCreditCard != null) {

            CreditCard creditCard = new CreditCard();

            try {
                creditCard.setNumber(Long.valueOf(count));
                creditCard.setCvv(Integer.valueOf(cvv));
                creditCard.setExpiration(expiration);
                creditCard.setCountId(Integer.valueOf(countId));
            } catch (Exception ex) {
                throw new AppException("You enter incorrect data");
            }

            if (manager.createCreditCard(creditCard, user.getLocale())) {

                request.setAttribute("infoHeader", Messages.getI18nMessage(Messages.MSG_CONGRATULATION, user.getLocale()));
                request.setAttribute("message", Messages.getI18nMessage(Messages.MSG_USER_CREDIT_CARD_SUCCESSFULLY_CREATED, user.getLocale()));

                forward = Path.PAGE_INFORMATION_PAGE;

            }


        } else {
            throw new AppException("Unexpected edit command");
        }


        return forward;
    }

}