package br.com.anderson.silva.apiconvert.controller;

import br.com.anderson.silva.apiconvert.dto.ConteudoDTO;
import br.com.anderson.silva.apiconvert.service.VideoService;
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
    public ResponseEntity<Map<String,String>> converter(ConteudoDTO urlDTO) {
        Map<String, String> response = new HashMap<>();
        response.put("json", "OK");
        return ResponseEntity.ok(response);
    }

}
