
package timeline;


    
public class GetFriendsCourse {
    private String fname;
    private String lname;
    private String course;

    public GetFriendsCourse( String fname, String lname,String course) {
        this.course = course;
        this.fname = fname;  
        this.lname = lname;  
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

   

   
    
    
}
