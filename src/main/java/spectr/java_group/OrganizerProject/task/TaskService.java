package spectr.java_group.OrganizerProject.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksByUser(Long userId){
        return taskRepository.findByUserId(userId);
    }

    public Optional<Task> getElementById(Long id){
        return  taskRepository.findById(id);
    }

    public void addTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
