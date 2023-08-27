package server;

import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;

@RestController
public class FileUploadController {

    private final MultipartProperties multipartProperties;

    public static String prefix = System.currentTimeMillis() + "_";

    FileUploadController(MultipartProperties multipartProperties) {
        this.multipartProperties = multipartProperties;
    }

    record FileResponse(String url) {}

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileResponse> upload(@RequestPart MultipartFile file) {
        String fileNameWithPrefix = prefix + file.getOriginalFilename();
        FileUtils.createDirectories(multipartProperties.getLocation());
        String destination = LocationUtils.fixEndSlash(multipartProperties.getLocation()) + fileNameWithPrefix;
        try {
            file.transferTo(Path.of(destination));
        } catch (IOException e) {
            throw FileUploadErrors.cantUploadFile();
        }
        String url = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .replacePath(LocationUtils.endSlash(multipartProperties.getLocation()))
                .path(LocationUtils.fixStartSlash(fileNameWithPrefix)).toUriString();
        return ResponseEntity.ok(new FileResponse(url));
    }
}
