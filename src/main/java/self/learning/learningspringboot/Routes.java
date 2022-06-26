package self.learning.learningspringboot;

public class Routes {

    public final static String API_VERSION = "api/v1";
    private final static String ID = "/{id}";
    private final static String LIST = "/get-list";

    private final static String CHANGE_STATUS = "/change-record-status";
    // for people
    public final static String PEOPLE_BASE_ROUTE = "/people";
    public final static String FIND_PEOPLE = PEOPLE_BASE_ROUTE + ID;
    public final static String PEOPLE_LIST = PEOPLE_BASE_ROUTE + LIST;
}
