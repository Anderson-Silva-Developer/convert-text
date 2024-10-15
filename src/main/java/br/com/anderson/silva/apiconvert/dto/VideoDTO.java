package br.com.anderson.silva.apiconvert.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoDTO {
    private String kind;
    private String etag;
    private List<Item> items;
    private PageInfo pageInfo;
}
