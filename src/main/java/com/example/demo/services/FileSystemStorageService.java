package com.example.demo.services;

import com.example.demo.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements StorageService{

    @Value("${storage.location}")
    private String storageLocation;

      @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException e) {
            throw new StorageException("Can not initialize the storage directory.");
        }
    }

    @Override
    public String storage(MultipartFile image) {
        String filename = image.getOriginalFilename();

        byte[] byteImage;
        try {
            byteImage = image.getBytes();
            Path fullPath = Paths.get(storageLocation + "//" +  image.getOriginalFilename());
            Files.write(fullPath, byteImage);
        } catch (IOException e) {
            throw new StorageException("Could not storage the file" + filename, e);
        }

        return filename;
    }

    /**
     *
     * @param filename
     * @return Path with a file name as a Path.
     */
    @Override
    public Path load(String filename) {
        return Paths.get(storageLocation).resolve(filename);
    }
}
