package pkg1.Entity.student;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "StudentAttendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subject;
	private LocalDate date;
	private String status; // Present, Absent, Late

	@ManyToOne
	private Student student;

	public Attendance(Long id, String subject, LocalDate date, String status, Student student) {
		super();
		this.id = id;
		this.subject = subject;
		this.date = date;
		this.status = status;
		this.student = student;
	}

	public Attendance() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public List<Attendance> setStudent(Student student) {
		this.student = student;
		return null;
	}

	public Attendance save(Attendance attendance) {
		// TODO Auto-generated method stub
		return null;
	}

}
