package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.ToDo;
import pkg1.Service.ToDoService;


import java.util.Optional;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/add")
    public ToDo addTask(@RequestBody ToDo task) {
        return toDoService.addTask(task);
    }

    @GetMapping("/student/{id}")
    public Optional<ToDo> getTasks(@PathVariable Long id) {
        return toDoService.getAllTasksByStudentId(id);
    }

    @PutMapping("/update/{id}")
    public Optional<ToDo> updateTask(@PathVariable Long id, @RequestBody ToDo task) {
        return toDoService.updateTask(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        toDoService.deleteTask(id);
    }
}
