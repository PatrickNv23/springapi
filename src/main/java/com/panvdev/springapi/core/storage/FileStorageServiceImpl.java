package com.panvdev.springapi.core.storage;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageServiceImpl(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try{
            Files.createDirectories(rootLocation);
        }catch (IOException e){
            throw new StorageException("Could not initialize storage: ", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String extension = StringUtils.getFilenameExtension(filename);
        String justFileName = filename.replace("." + extension, "");
        String newFilename = justFileName + "_" + UUID.randomUUID() + "." + extension;
        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file: " + newFilename);
            }
            if(newFilename.contains("..")){
                // This is a security check
                throw new StorageException("Cannot store file with relative path outside current directory: " + newFilename);
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, this.rootLocation.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
                return newFilename;
            }
        }catch(IOException e){
            throw new StorageException("Failed to store file " + newFilename, e);
        }
    }

    @Override
    public Resource download(String filename) {
        try{
            Path file = rootLocation.resolve(filename); // C:/web/springapi/guitar.png
            Resource resource = new UrlResource(file.toUri()); // uri -> file:///C:/web/springapi/guitar.png
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new StorageException("Could not read file: " + filename);
            }
        }catch (MalformedURLException e){
            throw new StorageException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(String filename) {
        try{
            Path file = rootLocation.resolve(filename);
            Files.deleteIfExists(file);
        }catch (IOException e){
            throw new StorageException("Could not delete file: " + filename, e);
        }
    }
}
