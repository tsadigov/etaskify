package com.tsadigov.etaskify.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String deadline;
    private String status;
    private List<String> usernames;
}
