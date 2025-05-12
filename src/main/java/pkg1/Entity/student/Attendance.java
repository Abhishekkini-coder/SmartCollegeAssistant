// src/main/java/pkg1/Entity/student/Attendance.java
package pkg1.Entity.student;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity(name = "StudentAttendance")
@Table(name = "student_attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private LocalDate date;
    private String status; // e.g. "Present", "Absent"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // --- Constructors ---
    public Attendance() {}

    public Attendance(String subject, LocalDate date, String status, Student student) {
        this.subject = subject;
        this.date    = date;
        this.status  = status;
        this.student = student;
    }

    // --- Getters & Setters ---
    public Long getId()                { return id; }
    public void setId(Long id)         { this.id = id; }

    public String getSubject()         { return subject; }
    public void setSubject(String s)   { this.subject = s; }

    public LocalDate getDate()         { return date; }
    public void setDate(LocalDate d)   { this.date = d; }

    public String getStatus()          { return status; }
    public void setStatus(String s)    { this.status = s; }

    public Student getStudent()        { return student; }
    public void setStudent(Student st) { this.student = st; }
}
