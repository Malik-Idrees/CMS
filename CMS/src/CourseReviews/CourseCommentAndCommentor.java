
package CourseReviews;

public class CourseCommentAndCommentor {

    private String comment;
    private String commentor;

    public CourseCommentAndCommentor(String commentor, String comment) {
        this.commentor = commentor;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    

    public String getCommentor() {
        return commentor;
    }

    public void setCommentor(String commentor) {
        this.commentor = commentor;
    }
    
}


