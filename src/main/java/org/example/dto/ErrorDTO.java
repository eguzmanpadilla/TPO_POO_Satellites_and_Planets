package org.example.dto;

import lombok.*;

@AllArgsConstructor
@Builder
public class ErrorDTO {

    private String type;
    private String message;

}
