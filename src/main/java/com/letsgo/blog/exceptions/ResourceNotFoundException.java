package com.letsgo.blog.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private long fieldValue;
    private String fieldLabel;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldLabel) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldLabel));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldLabel = fieldLabel;
    }
}
