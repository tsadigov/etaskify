package com.tsadigov.etaskify.domain;

import com.tsadigov.etaskify.config.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String deadline;
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL) // need to change
    @JoinTable(joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<AppUser> users;

}
