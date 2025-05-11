package pkg1.Service.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.student.ToDo;
import pkg1.Entity.student.ToDoRepo;

@Service
public class ToDoService {

  @Autowired
  private ToDoRepo toDoRepo;

  /** Fetch all tasks for a given student ID */
  public List<ToDo> getAllTasksByStudentId(Long studentId) {
    return toDoRepo.findByStudentId(studentId);
  }

  public ToDo addTask(ToDo task) {
    return toDoRepo.save(task);
  }

  public void deleteTask(Long id) {
    toDoRepo.deleteById(id);
  }

  public Optional<ToDo> updateTask(Long id, ToDo updatedTask) {
    return toDoRepo.findById(id).map(task -> {
      task.setTitle(updatedTask.getTitle());
      task.setDescription(updatedTask.getDescription());
      task.setCompleted(updatedTask.isCompleted());
      return toDoRepo.save(task);
    });
  }
}



