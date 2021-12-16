package com.tsadigov.etaskify.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDTO {
    private Integer code;
    private String message;
    private Object response;
}
