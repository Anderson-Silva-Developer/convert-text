package br.com.anderson.silva.apigetvideo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Snippet {
    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;
    private List<String> tags;
    private String categoryId;
    private String liveBroadcastContent;
    private String defaultLanguage;
    private Localized localized;
    private String defaultAudioLanguage;
}
