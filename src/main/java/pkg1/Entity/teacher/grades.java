package pkg1.Entity.teacher;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;
    private Long studentId;
    private String subject;
    private Double score;

    public grades() {}

    public grades(Long id, Long teacherId, Long studentId, String subject, Double score) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}
