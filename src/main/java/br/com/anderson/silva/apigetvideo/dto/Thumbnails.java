package br.com.anderson.silva.apigetvideo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thumbnails {
    @JsonProperty("default")
    private ThumbnailImage defaultImage;
    private ThumbnailImage medium;
    private ThumbnailImage high;
    private ThumbnailImage standard;
    private ThumbnailImage maxres;
}
