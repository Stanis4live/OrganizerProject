package spectr.java_group.OrganizerProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spectr.java_group.OrganizerProject.task.Task;
import spectr.java_group.OrganizerProject.task.TaskService;
import spectr.java_group.OrganizerProject.user.User;
import spectr.java_group.OrganizerProject.user.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        Optional<Task> task = taskService.getElementById(id);

        if (task.isEmpty() || !task.get().getUser().getEmail().equals(userDetails.getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(task.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails,
                                              @RequestBody Task updateTask){
        Optional<Task> existingTaskOpt = taskService.getElementById(id);

        if (existingTaskOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        Task existingTask = existingTaskOpt.get();

        if (!existingTask.getUser().getEmail().equals(userDetails.getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        existingTask.setTitle(updateTask.getTitle());
        existingTask.setDone(updateTask.getDone());

        taskService.addTask(existingTask);
        return ResponseEntity.ok("Task updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Task> tasks = taskService.getTasksByUser(user.get().getId());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<String> addTask(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Task task){
        if (userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
        if (userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();
        task.setUser(user);
        task.setDone(false);
        taskService.addTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        Optional<Task> existingTaskOpt = taskService.getElementById(id);

        if (existingTaskOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        Task existingTask = existingTaskOpt.get();

        if (!existingTask.getUser().getEmail().equals(userDetails.getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> toggleStatusTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        Optional<Task> existingTaskOpt = taskService.getElementById(id);

        if (existingTaskOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        Task existingTask = existingTaskOpt.get();

        if (!existingTask.getUser().getEmail().equals(userDetails.getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        existingTask.setDone(!existingTask.getDone());
        taskService.addTask(existingTask);
        return ResponseEntity.ok("Task updated successfully");
    }
}