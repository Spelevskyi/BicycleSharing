package by.epam.project.action.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.action.command.ActionCommand;
import by.epam.project.action.command.RoutePath;
import by.epam.project.action.command.RouteType;
import by.epam.project.action.command.Router;
import by.epam.project.exception.LogicException;
import by.epam.project.logic.Logic;
import by.epam.project.logic.admin.ChangeBillingLogic;
import by.epam.project.util.Constants;
import by.epam.project.util.PageError;

public class ChangeBillingCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangeBillingCommand.class);
    private Logic logic = new ChangeBillingLogic();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("Changing price list executing.");
        Router router = new Router();
        router.setType(RouteType.REDIRECT);
        String brand = request.getParameter(Constants.PRICE_LIST_BRAND);
        String unlockPrice = request.getParameter(Constants.PRICE_LIST_UNLOCK_PRICE);
        String perMinutePrice = request.getParameter(Constants.PRICE_LIST_PER_MINUTE);
        String perHourPrice = request.getParameter(Constants.PRICE_LIST_PER_HOUR);
        String stayPrice = request.getParameter(Constants.PRICE_LIST_STAY);
        String threeHourDiscount = request.getParameter(Constants.PRICE_LIST_THREE_HOUR_DISCOUNT);
        String sixHourDiscount = request.getParameter(Constants.PRICE_LIST_SIX_HOUR_DISCOUNT);
        String nineHourDiscount = request.getParameter(Constants.PRICE_LIST_NINE_HOUR_DISCOUNT);
        String daySale = request.getParameter(Constants.PRICE_LIST_ALL_DAY);
        String regularCustomerDiscount = request.getParameter(Constants.PRICE_LIST_REGULAR);
        String travelerDiscount = request.getParameter(Constants.PRICE_LIST_TRAVELER);
        String newCustomerDiscount = request.getParameter(Constants.PRICE_LIST_NEW);
        String id = request.getParameter(Constants.CHANGE_BILLING_ID);
        List<String> parameters = new ArrayList<>();
        parameters.add(brand);
        parameters.add(unlockPrice);
        parameters.add(perMinutePrice);
        parameters.add(perHourPrice);
        parameters.add(stayPrice);
        parameters.add(threeHourDiscount);
        parameters.add(sixHourDiscount);
        parameters.add(nineHourDiscount);
        parameters.add(daySale);
        parameters.add(regularCustomerDiscount);
        parameters.add(travelerDiscount);
        parameters.add(newCustomerDiscount);
        parameters.add(String.valueOf(id));
        try {
            logic.action(parameters);
            router.setRoutePath(RoutePath.REDIRECT_ADMIN_BILLING.getRoutePath());
            logger.info("Succesfully changing bicycle price list.");
        } catch (LogicException ex) {
            logger.error(ex);
            request.getSession().setAttribute(Constants.ERROR,
                    PageError.getError(Constants.TRUE, ex.getMessage()));
            router.setRoutePath(RoutePath.MESSAGE_PAGE_PATH.getRoutePath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}

