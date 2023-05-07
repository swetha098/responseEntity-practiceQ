package com.MOCK.PracticeAg.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class NameFoundException extends RuntimeException{
    public NameFoundException(String message){super(message);}
}
