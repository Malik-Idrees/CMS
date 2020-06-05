
package timeline;


public class GetAllStudents {
    private String id;
    private String fname;
    private String lname;
    private String semester;
    private String Email;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public GetAllStudents(String id, String fname, String lname, String semester, String Email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.semester = semester;
        this.Email = Email;
    }
    
    
}
