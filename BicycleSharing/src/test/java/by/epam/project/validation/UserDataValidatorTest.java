package by.epam.project.validation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserDataValidatorTest {
    
    /**
     * Test validate email true.
     *
     * @param email
     */
    @Test(dataProvider = "testEmailValidation")
    public void testValidateLoginTrue(String email) {
        Assert.assertTrue(UserDataValidator.isEmailValid(email));
    }

    /**
     * Test validate true email object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "testEmailValidation")
    public Object[][] testValidateTrueDataLogin() {
        return new Object[][]{
                { "belevich.ilya@gmail.com" }, { "pavel@gmail.com" }, { "alex@gmail.com" },
                { "anton.maceev@gmail.com" }, { "the_rock@gmail.com" },
        };
    }

    /**
     * Test validate password true.
     *
     * @param password the password
     */
    @Test(dataProvider = "testPasswordValid")
    public void testValidatePasswordTrue(String password) {
        Assert.assertTrue(UserDataValidator.isPasswordValid(password));

    }

    /**
     * Test validate true data password object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "testPasswordValid")
    public Object[][] testValidateTrueDataPassword() {
        return new Object[][] { { "123123wwe" }, { "765432" }, { "18reww1" }, { "1uueuQxc1" }, };
    }
}
