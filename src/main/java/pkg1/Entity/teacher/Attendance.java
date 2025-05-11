package pkg1.Entity.teacher;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TeacherAttendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;
    private Long studentId;
    private LocalDate date;
    private boolean present;

    public Attendance() {}

    public Attendance(Long id, Long teacherId, Long studentId, LocalDate date, boolean present) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}
