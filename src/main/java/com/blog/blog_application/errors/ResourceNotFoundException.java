package com.blog.blog_application.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resouceName;
    String fieldName;
    Integer feildValue;

    public ResourceNotFoundException(String resource, String fieldName, Integer fieldValue) {
        super(String.format("%s not found with %s : %d", resource, fieldName, fieldValue));
        this.resouceName = resource;
        this.fieldName = fieldName;
        this.feildValue = fieldValue;
    }
    
}
