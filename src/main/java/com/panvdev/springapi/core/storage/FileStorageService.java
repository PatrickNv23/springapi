package com.panvdev.springapi.core.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void init();
    String store(MultipartFile file);
    Resource download(String filename);
    void delete(String filename);
}
