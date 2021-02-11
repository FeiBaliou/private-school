package entities;

/**
 *
 * @author feiba
 */
public class StudentsPerCourse {
    private int course;
    private int student;

    public StudentsPerCourse(int course, int student) {
        this.course = course;
        this.student = student;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentsPerCourse{" + "course=" + course + ", student=" + student + '}';
    }
    
    
    
    
    
}
