package com.online.edu.onvidservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface VidService {
    String uploadVideoAlyun(MultipartFile file);

    void deleteAliyunVideoId(String videoId);
}
