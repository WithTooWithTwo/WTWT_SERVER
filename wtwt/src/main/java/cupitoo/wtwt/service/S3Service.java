package cupitoo.wtwt.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import cupitoo.wtwt.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;
    private final String PREFIX =  ".s3.ap-northeast-2.amazonaws.com";
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Image uploadImage(MultipartFile multipartFile) {
        if(multipartFile.getOriginalFilename().isEmpty()) return null;
        String fileName = createFileName(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try {
            InputStream inputStream = multipartFile.getInputStream();
            amazonS3.putObject(
                    new PutObjectRequest(bucket + "/image",
                            fileName, inputStream,
                            objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
            return new Image("https://" + bucket + PREFIX + "/image/" + fileName);
        } catch (Exception e) {
            throw new NotFoundException(" ... ");
        }
    }

    public List<Image> uploadImageList(List<MultipartFile> multipartFileList) {
        List<Image> images = multipartFileList.stream()
                .map(multipartFile -> uploadImage(multipartFile))
                .collect(Collectors.toList());
        return images;
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new NotFoundException("INVALID_IMAGE_EXCEPTION");
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException();
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
