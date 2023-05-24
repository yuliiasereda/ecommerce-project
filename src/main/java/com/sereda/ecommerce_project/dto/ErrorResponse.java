package com.sereda.ecommerce_project.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
