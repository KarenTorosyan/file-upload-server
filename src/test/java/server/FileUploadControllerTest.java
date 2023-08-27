package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FileUploadController.class)
@Import(MultipartProperties.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MultipartProperties multipartProperties;

    @Test
    void shouldUploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                "file.jpg", "image/jpeg", new byte[]{});
        String filePrefix = "prefix_";
        FileUploadController.prefix = filePrefix;
        String uri = ServletUriComponentsBuilder.fromRequest(new MockHttpServletRequest())
                .path(LocationUtils.endSlash(multipartProperties.getLocation()))
                .path("/" + filePrefix + file.getOriginalFilename()).toUriString();
        mockMvc.perform(multipart("/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("url").value(uri));
    }
}
