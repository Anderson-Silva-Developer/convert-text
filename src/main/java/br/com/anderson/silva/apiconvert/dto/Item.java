package br.com.anderson.silva.apiconvert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String kind;
    private String etag;
    private String id;
    private Snippet snippet;
    private ContentDetails contentDetails;
}
