package com.binary.myhospital.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
