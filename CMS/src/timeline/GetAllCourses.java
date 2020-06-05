
package timeline;

import java.sql.Time;


public class GetAllCourses {
    private String id;
    private String location;
    private Time start;
    private Time end;
    private int semester;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public GetAllCourses(String id, String location, Time start, Time end, int semester) {
        this.id = id;
        this.location = location;
        this.start = start;
        this.end = end;
        this.semester=semester;
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
