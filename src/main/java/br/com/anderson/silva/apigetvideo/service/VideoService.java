package br.com.anderson.silva.apigetvideo.service;

import br.com.anderson.silva.apigetvideo.client.YoutubeClient;
import br.com.anderson.silva.apigetvideo.dto.Item;
import br.com.anderson.silva.apigetvideo.dto.VideoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class VideoService {
    private final YoutubeClient youtubeClient;
    @Value("${key}")
    private String key;

    public VideoService(YoutubeClient youtubeClient) {
        this.youtubeClient = youtubeClient;
    }

    public Map<String, String> getVideo(String url)  {
        String videoId = url.split("v=")[1];
        VideoDTO video = youtubeClient.search("snippet,contentDetails", videoId,key);
        Map<String, String> response = new HashMap<>();

        Item item = video.getItems().get(0);
        String duration = item.getContentDetails().getDuration();
        Duration durationParsed = Duration.parse(duration);

        long minutes = durationParsed.toMinutes();
        long seconds = durationParsed.getSeconds() % 60;
        response.put("duration", String.valueOf(minutes) + " : " + String.valueOf(seconds));
        response.put("title", item.getSnippet().getTitle());
        response.put("imgVideo", item.getSnippet().getThumbnails().getMedium().getUrl());

        return response;

    }


}
