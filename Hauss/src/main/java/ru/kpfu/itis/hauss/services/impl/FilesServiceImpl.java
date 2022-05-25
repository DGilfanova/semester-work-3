package ru.kpfu.itis.hauss.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.exceptions.FileNotFoundException;
import ru.kpfu.itis.hauss.exceptions.StorageException;
import ru.kpfu.itis.hauss.services.FilesService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilesServiceImpl implements FilesService {

    private final Path rootLocation;

    public FilesServiceImpl(@Value("${storage.location}") String storagePath) {
        this.rootLocation = Path.of(storagePath);
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String upload(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String storageFileName = UUID.randomUUID() + extension;

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(storageFileName));
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }

        return storageFileName;
    }

    @Override
    public List<String> upload(MultipartFile[] multipartList) {
        return Arrays.stream(multipartList)
                .map(this::upload)
                .collect(Collectors.toList());
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException("File not found");
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }
}

