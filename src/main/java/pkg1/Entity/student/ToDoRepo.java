package pkg1.Entity.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ToDo, Long> {
    /** Find all tasks belonging to a student (by student’s ID) */
    List<ToDo> findByStudentId(Long studentId);
}


