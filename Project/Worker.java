package Project;

import java.util.ArrayList;

public interface Worker {
    public abstract void changeAttribute(String attribute, String newValue);
    public abstract String getData(String attribute);
    //Unexpected changes void promote/demote() -> Worker promote/demote()
    public abstract Worker promote();
    public abstract Worker demote();
}