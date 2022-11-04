package com.example.kibanatestback.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElkLoginRequestDTO {
    private String providerType = "basic";
    private String providerName = "basic"; // If you are using cloud version, the value is then cloud-basic
    private String currentURL = "/";
    private ElkLoginParamsRequestDTO params;
}