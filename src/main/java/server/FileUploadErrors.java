package server;

import org.springframework.util.unit.DataSize;

public class FileUploadErrors {

    public static FileUploadException cantUploadFile() {
        return new FileUploadException("Can't upload file!");
    }

    public static FileUploadException fileSizeLimit(DataSize dataSize) {
        return new FileUploadException("File size limit " + dataSize.toMegabytes() + "mb!");
    }
}
