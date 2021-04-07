package it.corso.microservizi.provaspring.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private Date timestamp;
}
