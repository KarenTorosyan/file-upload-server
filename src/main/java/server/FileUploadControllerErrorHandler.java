package server;

import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class FileUploadControllerErrorHandler {

    private final MultipartProperties multipartProperties;

    FileUploadControllerErrorHandler(MultipartProperties multipartProperties) {
        this.multipartProperties = multipartProperties;
    }

    record FileUploadError(String message) {}
    record FileUploadErrorResponse(FileUploadError error) {}

    @ExceptionHandler(FileUploadException.class)
    ResponseEntity<FileUploadErrorResponse> handle(FileUploadException e) {
        return new ResponseEntity<>(new FileUploadErrorResponse(
                new FileUploadError(e.getMessage())
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    ResponseEntity<FileUploadErrorResponse> handle() {
        return new ResponseEntity<>(new FileUploadErrorResponse(new FileUploadError(
                FileUploadErrors.fileSizeLimit(multipartProperties.getMaxFileSize()).getMessage())
        ), HttpStatus.BAD_REQUEST);
    }
}
