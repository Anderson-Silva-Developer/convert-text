package br.com.anderson.silva.apigetvideo.service;

import br.com.anderson.silva.apigetvideo.client.YoutubeClient;
import br.com.anderson.silva.apigetvideo.dto.Item;
import br.com.anderson.silva.apigetvideo.dto.VideoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
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

    public Map<String, String> getVideo(String url) {
        String videoId = url.split("v=")[1];
        VideoDTO video = youtubeClient.search("snippet,contentDetails", videoId, key);
        Map<String, String> response = new HashMap<>();

        Item item = video.getItems().get(0);
        String duration = item.getContentDetails().getDuration();
        Duration durationParsed = Duration.parse(duration);

        long minutes = durationParsed.toMinutes();
        long seconds = durationParsed.getSeconds() % 60;
        response.put("duration", String.valueOf(minutes) + " : " + String.valueOf(seconds));
        response.put("title", item.getSnippet().getTitle());
        response.put("imgVideo", item.getSnippet().getThumbnails().getMedium().getUrl());
        try {
            String videoBase64 = response.put("video", downloadAndConvertToBase64(item.getId()));
            response.put("video", videoBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    public String downloadAndConvertToBase64(String youtubeUrl) throws Exception {
        String videoUrl = youtubeUrl;
        String outputFileName = "video.mp4";
        String ytDlpPath = "/opt/venv/bin/yt-dlp";

        try {
            // Baixar o vídeo usando yt-dlp
            ProcessBuilder builder = new ProcessBuilder(
                    ytDlpPath,
                    "-o", outputFileName,
                    videoUrl
            );
            Process process = builder.start();
            process.waitFor();

            // Ler o arquivo MP4 e codificar em Base64
            File videoFile = new File(outputFileName);
            byte[] fileContent = Files.readAllBytes(videoFile.toPath());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            System.out.println("Base64 Encoded Video: " + encodedString);
            return encodedString;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }


}
