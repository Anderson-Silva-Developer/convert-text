package br.com.anderson.silva.apiconvert.controller;

import br.com.anderson.silva.apiconvert.dto.ConteudoDTO;
import br.com.anderson.silva.apiconvert.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ArquivoController {
    private final ConverterService converterService;

    public ArquivoController(ConverterService converterService) {
        this.converterService = converterService;
    }
    @PostMapping("/converter")
    public ResponseEntity<Map<String,String>> converter(ConteudoDTO conteudoDTO) {
        return ResponseEntity.ok(converterService.converter(conteudoDTO));
    }

}
