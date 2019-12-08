package by.epam.project.action.command;

public enum RoutePath {

    USER_MAIN_PAGE_PATH("/jsp/user/user.jsp"), ADMIN_MAIN_PAGE_PATH("/jsp/admin/admin.jsp"),
    INDEX_PAGE_PATH("/index.jsp"), ACCOUNT_PAGE_PATH("/jsp/account.jsp"),
    USER_CHANGE_PROFILE_PAGE_PATH("/jsp/user/change_profile.jsp"),
    ADMIN_CHANGE_PROFILE_PAGE_PATH("/jsp/admin/change_profile.jsp"),
    RENTAL_POINT_PAGE_PATH("/jsp/user/rental_point.jsp"),
    ORDER_PAGE_PATH("/jsp/user/order.jsp"), USER_INFO_PAGE_PATH("/jsp/admin/users.jsp"),
    CONFIRMATION_PAGE_PATH("/jsp/confirm.jsp"), BICYCLES_PAGE_PATH("/jsp/admin/bicycles.jsp"),
    CONFIRMATION_USER_PAGE_PATH("/jsp/user/confirm.jsp"),
    MESSAGE_PAGE_PATH("/jsp/message.jsp"),
    POINTS_PAGE_PATH("/jsp/admin/points.jsp"), ADMIN_BILLING_PAGE_PATH("/jsp/admin/billing.jsp"),
    USER_BILLING_PAGE_PATH("/jsp/user/billing.jsp"), ADMIN_CHANGE_BILLING_PAGE_PATH("/jsp/admin/change_billing.jsp"),
    FAVORITES_PAGE_PATH("/jsp/user/favorites.jsp"), CHANGE_BICYCLE_PAGE_PATH("/jsp/admin/change_bicycle.jsp"),
    REDIRECT_ADMIN_HOME("controller?command=Admin_home_page"), REDIRECT_USER_HOME("controller?command=User_home_page"),
    REDIRECT_ACCOUNT_PAGE("controller?command=Account_page"),
    REDIRECT_ADMIN_BILLING("controller?command=Admin_billing_page"),
    REDIRECT_USER_BILLING("controller?command=User_billing_page"), REDIRECT_ADMIN_POINTS("controller?command=Points"),
    REDIRECT_USER_POINTS("controller?command=User_points"), REDIRECT_BICYCLE_PAGE("controller?command=Bicycles"),
    REDIRECT_USERS_PAGE("controller?command=Users"), REDIRECT_CONFIRM_PAGE("controller?command=Confirm_page"),
    REDIRECT_HOME_PAGE("controller?command=Home_page");

    private String routePath;

    RoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }
}
