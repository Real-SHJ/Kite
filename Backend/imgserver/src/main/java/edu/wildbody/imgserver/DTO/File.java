package edu.wildbody.imgserver.DTO;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class File {
    private List<MultipartFile> files;
}
