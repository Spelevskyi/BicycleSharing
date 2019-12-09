package by.epam.project.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.project.entity.user.RoleType;

public class WelcomeTag extends TagSupport {

    private String name;
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Custom tag
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            String result = null;
            if (RoleType.ADMIN.toString().equals(role)) {
                result = "Hello, " + name;
            } else {
                result = "Welcome, " + name;
            }
            pageContext.getOut().write(result);
        } catch (IOException ex) {
            throw new JspException(ex.getMessage());
        }
        return SKIP_BODY;
    }
}
