package com.eduhub.eduhub_backend.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String resource,String field,String error){
        super(String.format("Resource %s not found with %s:%s", resource,field,error));
    }
}
