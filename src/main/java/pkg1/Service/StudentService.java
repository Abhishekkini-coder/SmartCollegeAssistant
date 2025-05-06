package pkg1.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pkg1.Entity.Student;
import pkg1.Entity.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Student registerStudent(Student student) {
        // Encrypt password before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

	public boolean validateLogin(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}