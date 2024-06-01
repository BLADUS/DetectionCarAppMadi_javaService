package com.example.DetectionCarAppMadi_javaService.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class VideoController {
    @Value("${flask.url}")
    private String flaskUrl;

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        return "upload-video-form";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("video") MultipartFile file) {
        try {
            // Инициализация RestTemplate для HTTP-запроса
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flaskUrl + "/upload_video");

            // Создание ресурсов для файла
            ByteArrayResource videoResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            // Создание заголовков для запроса
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Создание сущности HTTP-запроса
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("video", videoResource);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Отправка видео на Flask-сервер
            ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), requestEntity,
                    String.class);

            // Возврат успешного ответа
            return new ResponseEntity<>("Video uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Video upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
