package br.com.anderson.silva.apigetvideo.client;

import br.com.anderson.silva.apigetvideo.dto.VideoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "youtubeClient", url = "${microsservicos.youtube.url}")
public interface YoutubeClient {

    @GetMapping
    VideoDTO search(@RequestParam("part") String part,
                    @RequestParam("id") String id,
                    @RequestParam("key") String apiKey);
}
