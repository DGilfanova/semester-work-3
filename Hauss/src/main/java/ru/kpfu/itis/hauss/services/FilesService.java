package ru.kpfu.itis.hauss.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FilesService {
    void init();
    String upload(MultipartFile file);
    List<String> upload(MultipartFile[] multipartList);
    Path load(String filename);
    Resource loadAsResource(String filename);
}
