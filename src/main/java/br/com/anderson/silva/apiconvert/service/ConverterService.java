package br.com.anderson.silva.apiconvert.service;

import br.com.anderson.silva.apiconvert.dto.ConteudoDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;
import org.json.XML;

@Service
public class ConverterService {

    public Map<String, String> converter(ConteudoDTO conteudoDTO) {
        if (Objects.nonNull(conteudoDTO.getOpcao()) && conteudoDTO.getOpcao() == 1) {
            conteudoDTO.setJson(convertXmlToJson(conteudoDTO.getXml()));
        }
        if (Objects.nonNull(conteudoDTO.getOpcao()) && conteudoDTO.getOpcao() == 2) {
            conteudoDTO.setXml(convertJsonToXml(conteudoDTO.getJson()));
        }
        Map<String, String> response = new HashMap<>();
        response.put("json", conteudoDTO.getJson());
        response.put("xml", conteudoDTO.getXml());
        return response;
    }


    public String convertXmlToJson(String xml) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xml);
            String jsonString = jsonObject.toString(4);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
            return "formato xml não é válido";
        }
    }
    public String convertJsonToXml(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String xmlString = XML.toString(jsonObject);
            return xmlString;
        } catch (Exception e) {
            e.printStackTrace();
            return "formato json não é válido";
        }
    }
}
