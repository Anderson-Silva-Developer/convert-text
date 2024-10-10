package br.com.anderson.silva.apigetvideo.controller;

import br.com.anderson.silva.apigetvideo.dto.UrlDTO;
import br.com.anderson.silva.apigetvideo.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ArquivoController {
    private final VideoService videoService;

    public ArquivoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/converter")
    public ResponseEntity<Map<String, String>> converter( UrlDTO urlDTO) {
        return ResponseEntity.ok(videoService.getVideo(urlDTO.getUrl()));
    }
    @PostMapping("/download")
    public ResponseEntity<Map<String, String>> download(UrlDTO urlDTO) {
        videoService.getVideo(urlDTO.getUrl());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Download realizado com sucesso");
        return ResponseEntity.ok(response);
    }


}
