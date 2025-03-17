package spectr.java_group.OrganizerProject.task;

import jakarta.persistence.*;
import spectr.java_group.OrganizerProject.user.User;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;
    private Boolean done;

    public Task(){}

    public Task(User user, String title, Boolean done) {
        this.user = user;
        this.title = title;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "user=" + user +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
