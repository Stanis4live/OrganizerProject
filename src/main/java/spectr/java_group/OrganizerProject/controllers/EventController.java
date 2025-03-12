package spectr.java_group.OrganizerProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import spectr.java_group.OrganizerProject.event.Event;
import spectr.java_group.OrganizerProject.event.EventService;
import spectr.java_group.OrganizerProject.user.User;
import spectr.java_group.OrganizerProject.user.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getUserEvents(@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Event> events = eventService.getEventsByUser(user.get().getId());
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<String> addEvent(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Event event){
        if (userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
        if (userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();
        event.setUser(user);
        eventService.addEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED).body("Event created successfully.");
    }
}