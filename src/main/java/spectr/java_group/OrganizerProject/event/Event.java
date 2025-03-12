package spectr.java_group.OrganizerProject.event;


import jakarta.persistence.*;
import spectr.java_group.OrganizerProject.user.User;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    @Column
    private LocalDateTime dateTime;

    @Lob
    private String description;

    public Event(){}

    public Event(User user, String title, LocalDateTime dateTime, String description) {
        this.user = user;
        this.title = title;
        this.dateTime = dateTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}
