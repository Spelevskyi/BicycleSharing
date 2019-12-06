package by.epam.project.action.command;

import by.epam.project.action.command.admin.AddBicycleCommand;
import by.epam.project.action.command.admin.AddBicycleWithLocationCommand;
import by.epam.project.action.command.admin.AddBillingCommand;
import by.epam.project.action.command.admin.AdminAccountPageCommand;
import by.epam.project.action.command.admin.AdminBillingPageCommand;
import by.epam.project.action.command.admin.AdminChangeAvatarCommand;
import by.epam.project.action.command.admin.AdminChangeProfileCommand;
import by.epam.project.action.command.admin.AdminHomeCommand;
import by.epam.project.action.command.admin.BicyclesCommand;
import by.epam.project.action.command.admin.ChangeBillingCommand;
import by.epam.project.action.command.admin.DeleteBicycleCommand;
import by.epam.project.action.command.admin.DeleteBillingCommand;
import by.epam.project.action.command.admin.DeleteUserCommand;
import by.epam.project.action.command.admin.DisableBicycleCommand;
import by.epam.project.action.command.admin.EnableBicycleCommand;
import by.epam.project.action.command.admin.LockUserCommand;
import by.epam.project.action.command.admin.PointsCommand;
import by.epam.project.action.command.admin.UnlockUserCommand;
import by.epam.project.action.command.admin.UsersCommand;
import by.epam.project.action.command.common.ChangePasswordCommand;
import by.epam.project.action.command.common.ForgotPasswordCommand;
import by.epam.project.action.command.common.LanguageCommand;
import by.epam.project.action.command.common.LogoutCommand;
import by.epam.project.action.command.guest.LoginCommand;
import by.epam.project.action.command.guest.RegisterCommand;
import by.epam.project.action.command.impl.AddRentalPointCommand;
import by.epam.project.action.command.impl.ConfirmUserPageCommand;
import by.epam.project.action.command.user.ConfirmUserCommand;
import by.epam.project.action.command.user.CreateCardCommand;
import by.epam.project.action.command.user.DeleteCardCommand;
import by.epam.project.action.command.user.DisableCardCommand;
import by.epam.project.action.command.user.EnableCardCommand;
import by.epam.project.action.command.user.EndMoveCommand;
import by.epam.project.action.command.user.FavoritesPageCommand;
import by.epam.project.action.command.user.OrderCreateCommand;
import by.epam.project.action.command.user.PayDebtCommand;
import by.epam.project.action.command.user.RentalPointsCommand;
import by.epam.project.action.command.user.ReplenishBalanceCommand;
import by.epam.project.action.command.user.StartMoveCommand;
import by.epam.project.action.command.user.UserAccountPageCommand;
import by.epam.project.action.command.user.UserBillingPageCommand;
import by.epam.project.action.command.user.UserChangeAvatarCommand;
import by.epam.project.action.command.user.UserChangeProfileCommand;
import by.epam.project.action.command.user.UserHomeCommand;

public enum CommandType {
    // Guest commands
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },
    // Common commands for opening pages
    USER_HOME_PAGE {
        {
            this.command = new UserHomeCommand();
        }
    },
    ADMIN_HOME_PAGE {
        {
            this.command = new AdminHomeCommand();
        }
    },
    PAY_DEBT {
        {
            this.command = new PayDebtCommand();
        }
    },
    DELETE_CARD {
        {
            this.command = new DeleteCardCommand();
        }
    },
    DISABLE_CARD {
        {
            this.command = new DisableCardCommand();
        }
    },
    ENABLE_CARD {
        {
            this.command = new EnableCardCommand();
        }
    },
    ADMIN_ACCOUNT_PAGE {
        {
            this.command = new AdminAccountPageCommand();
        }
    },
    USER_ACCOUNT_PAGE {
        {
            this.command = new UserAccountPageCommand();
        }
    },
    USER_BILLING_PAGE {
        {
            this.command = new UserBillingPageCommand();
        }
    },
    ADMIN_BILLING_PAGE {
        {
            this.command = new AdminBillingPageCommand();
        }
    },
    ENABLE_BICYCLE {
        {
            this.command = new EnableBicycleCommand();
        }
    },
    DISABLE_BICYCLE {
        {
            this.command = new DisableBicycleCommand();
        }
    },
    CHANGE_PROFILE_PAGE {
        {
            this.command = new AdminChangeProfileCommand();
        }
    },
    USER_CHANGE_PROFILE {
        {
            this.command = new UserChangeProfileCommand();
        }
    },
    ADMIN_CHANGE_PROFILE {
        {
            this.command = new AdminChangeProfileCommand();
        }
    },
    ORDER {
        {
            this.command = new OrderCreateCommand();
        }
    },
    CHANGE_LANGUAGE {
        {
            this.command = new LanguageCommand();
        }
    },
    CHANGE_BILLING {
        {
            this.command = new ChangeBillingCommand();
        }
    },
    ADD_BICYCLE_WITH_LOCATION {
        {
            this.command = new AddBicycleWithLocationCommand();
        }
    },
    ADD_BILLING {
        {
            this.command = new AddBillingCommand();
        }
    },
    DELETE_BILLING {
        {
            this.command = new DeleteBillingCommand();
        }
    },
    USERS {
        {
            this.command = new UsersCommand();
        }
    },
    USER_POINTS {
        {
            this.command = new RentalPointsCommand();
        }
    },
    CONFIRM{
        {
            this.command = new ConfirmUserCommand();
        }
    },
    CONFIRM_PAGE {
        {
            this.command = new ConfirmUserPageCommand();
        }
    },

    START_MOVE {
        {
            this.command = new StartMoveCommand();
        }
    },
    END_MOVE {
        {
            this.command = new EndMoveCommand();
        }
    },
    FORGOT_PASSWORD {
        {
            this.command = new ForgotPasswordCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    LOCK_USER {
        {
            this.command = new LockUserCommand();
        }
    },
    UNLOCK_USER {
        {
            this.command = new UnlockUserCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    BICYCLES {
        {
            this.command = new BicyclesCommand();
        }
    },
    DELETE_BICYCLE {
        {
            this.command = new DeleteBicycleCommand();
        }
    },
    ADD_BICYCLE {
        {
            this.command = new AddBicycleCommand();
        }
    },
    USER_CHANGE_AVATAR {
        {
            this.command = new UserChangeAvatarCommand();
        }
    },
    ADMIN_CHANGE_AVATAR {
        {
            this.command = new AdminChangeAvatarCommand();
        }
    },
    ADD_POINT {
        {
            this.command = new AddRentalPointCommand();
        }
    },
    POINTS {
        {
            this.command = new PointsCommand();
        }
    },
    CREATE_CARD {
        {
            this.command = new CreateCardCommand();
        }
    },
    REPLENISH_BALANCE {
        {
            this.command = new ReplenishBalanceCommand();
        }
    },
    FAVORITES {
        {
            this.command = new FavoritesPageCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
