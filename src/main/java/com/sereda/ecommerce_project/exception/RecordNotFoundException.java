package com.sereda.ecommerce_project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordNotFoundException extends RuntimeException {
    private Class clazz;
    private String id;
}
