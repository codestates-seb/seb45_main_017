package recipe.server.recipes.controller;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import recipe.server.recipes.entity.Recipes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
@Component
public class ImageHandler {

    public List<Recipes> parseImageInfo(long recipesId, List<ImageFiles> imageFiles) throws Exception{

        // 반환 할 파일 리스트
        List<Recipes> imageList = new ArrayList<>();

        // 빈 파일 -> 빈 것 반환
        if( imageFiles.isEmpty()) {
            return imageList;
        }

        // 파일 이름 -> 업로드 한 날짜로 바꾸어 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 절대경로 설정
        String absolutePath = new File("").getAbsolutePath() + "\\";

        // 경로 저장 -> 경로에 저장
        String path = "images/" + current_date;
        File file = new File(path);

        // 저장할 위치의 디렉토리가 존재하지 않을 경우
        if(!file.exists()) {
            file.mkdirs();
        }

        for (ImageFiles imageFile : imageFiles) {
            if(!imageFile.isEmpty()) {
                String contentType = imageFile.getContentType();
                String originalFilesExtension;

                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if(contentType.contains("image/jpeg")) {
                        originalFilesExtension = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        originalFilesExtension = ".png";
                    } else if(contentType.contains("image/gif")) {
                        originalFilesExtension = ".gif";
                    } else {
                        break;
                    }
                }

                String newFileName = System.nanoTime() + originalFilesExtension;

                Recipes recipes = Recipes.builder()
                        .recipesId(recipesId)
                        .originalFileName(imageFile.getOriginalFileName())
                        .storedFileName(path + "/" + newFileName)
                        .fileSize(imageFile.getSize())
                        .build();
                imageList.add(recipes);

                file = new File(absolutePath + path + "/" + newFileName);
                imageFile.transferTo(file);
            }
        }
        return imageList;
    }
}

 */
