package com.pe.HeoComisiones.Exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DniException extends RuntimeException{
    private String message;
}
