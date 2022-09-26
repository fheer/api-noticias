package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * Storage Service Interface.
 *
 * @author Oscar Lopez
 * @version 0.1
 */
public interface StorageService {

    void init();
    String storage(MultipartFile file);
    Path load(String filename);
}
