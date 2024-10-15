package br.com.anderson.silva.apiconvert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDetails {
    private String duration;
    private String dimension;
    private String definition;
    private boolean caption;
    private boolean licensedContent;
    private String projection;
}
