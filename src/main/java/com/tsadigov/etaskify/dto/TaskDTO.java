package com.tsadigov.etaskify.dto;

import com.tsadigov.etaskify.domain.TaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskDTO {

    private String title;
    private String description;
    private String deadline;
    private TaskStatus status;
    private List<String> usernames;

}
