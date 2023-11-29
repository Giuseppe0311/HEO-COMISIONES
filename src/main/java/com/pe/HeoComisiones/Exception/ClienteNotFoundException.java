package com.pe.HeoComisiones.Exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNotFoundException extends RuntimeException{
    private String message;
}
