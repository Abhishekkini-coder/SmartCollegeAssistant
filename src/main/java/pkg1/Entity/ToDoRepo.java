package pkg1.Entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ToDo,Long>{
	Optional<ToDo> findByStudent(Student student);
	

}
