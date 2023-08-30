package recipe.server.recipes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.recipes.entity.Images;
import recipe.server.recipes.repository.ImageRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


//    @Value("${image.upload.directory}")
    @Value("/User/images/Desktop")
    private String uploadDirectory; // 이미지를 저장할 디렉토리

    @Transactional
    public String uploadAndSaveImage(MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                LocalDateTime now = LocalDateTime.now();
                int year = now.getYear();
                int month = now.getMonthValue();
                int day = now.getDayOfMonth();
                int hour = now.getHour();
                int minute = now.getMinute();
                int second = now.getSecond();
                int millis = now.get(ChronoField.MILLI_OF_SECOND);

                String absolutePath = new File(uploadDirectory).getAbsolutePath() + "/";
                String originalFilename = imageFile.getOriginalFilename();

                String newFileName = "image" + hour + minute + second + millis;
                String fileExtension = '.' + originalFilename.replaceAll("^.*\\.(.*)$", "$1");
                String path = "images/local/" + year + "/" + month + "/" + day;

                File file = new File(absolutePath + path);
                if (!file.exists()) {
                    file.mkdirs();
                }

                file = new File(absolutePath + path + "/" + newFileName + fileExtension);
                imageFile.transferTo(file);

                Images image = Images.builder()
                        .originImageName(originalFilename)
                        .imageName(newFileName + fileExtension)
                        .imagePath(path)
                        .build();

                imageRepository.save(image);

                return absolutePath;
            } catch (IOException e) {
                throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
            }
        } else {
            throw new IllegalArgumentException("이미지 파일이 없습니다.");
        }
    }

//    @Value("/User/KIM/Desktop")
//    private String uploadDirectory; // 이미지를 저장할 디렉토리
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    public String saveImage(MultipartFile imageFile) {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            try {
//                // 이미지 파일을 저장할 디렉토리를 생성
//                File directory = new File(uploadDirectory);
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//
//                // 이미지 파일의 고유한 이름 생성
//                String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
//                String filePath = uploadDirectory + File.separator + uniqueFileName;
//
//                // 이미지 파일을 서버에 저장
//                File file = new File(filePath);
//                imageFile.transferTo(file);
//
//                // 이미지 정보를 DB에 저장
//                Images image = new Images();
//                image.setOriginImageName(imageFile.getOriginalFilename());
//                image.setImageName(uniqueFileName);
//                image.setImagePath(filePath);
//                imageRepository.save(image);
//
//                return filePath; // 업로드된 이미지의 경로 반환
//            } catch (IOException e) {
//                throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
//            }
//        } else {
//            throw new IllegalArgumentException("이미지 파일이 없습니다.");
//        }
//    }
}
