package com.example.Vaccination.Management.Exceptions;

public class EmailAlreadyExistsException extends Exception{
     public EmailAlreadyExistsException(String message){
         super(message);
     }
}
