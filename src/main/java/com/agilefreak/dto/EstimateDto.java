package com.agilefreak.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EstimateDto {

    public String estimate;
    public String check;
    
    public EstimateDto(){}

    public EstimateDto(String estimate, String check) {
        this.estimate = estimate;
        this.check = check;
    }
}
