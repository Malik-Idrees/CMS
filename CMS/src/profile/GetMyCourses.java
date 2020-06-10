package profile;

import java.sql.Time;

//This class is Used to create an instances of courses so we can display them in a list/table
public class GetMyCourses {

    private String id;
    private String location;
    private Time start;
    private Time end;

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public GetMyCourses(String id, String location, Time start, Time end) {
        this.id = id;
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

}
