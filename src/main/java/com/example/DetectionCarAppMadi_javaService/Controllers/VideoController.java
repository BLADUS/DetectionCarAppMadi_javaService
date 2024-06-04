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

import com.example.DetectionCarAppMadi_javaService.Entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class VideoController {
    @Value("${flask.url}")
    private String flaskUrl;

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        return "upload-video-form";
    }

   @PostMapping("/upload")
public ResponseEntity<String> handleFileUpload(@RequestParam("video") MultipartFile file, HttpSession session) {
    try {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }

        String username = currentUser.getUsername();

        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(flaskUrl + "/upload_video");

        ByteArrayResource videoResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("video", videoResource);
        body.add("username", username);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), requestEntity, String.class);

        return new ResponseEntity<>("Video uploaded successfully", HttpStatus.OK);
    } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Video upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
