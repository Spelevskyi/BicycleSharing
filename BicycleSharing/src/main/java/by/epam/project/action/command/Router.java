package by.epam.project.action.command;

public class Router {
    private String path;
    private RouteType type = RouteType.FORWARD;

    public String getRoutePath() {
        return path;
    }

    public void setRoutePath(String path) {
        this.path = path;
    }

    public RouteType getType() {
        return type;
    }

    public void setType(RouteType type) {
        if (type != null) {
            this.type = type;
        }
    }

}
