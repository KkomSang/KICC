package kicc.cicd.converter;

import kicc.cicd.domain.Diary;
import kicc.cicd.domain.Image;

public class ImageConverter {
    public static Image toImage(Diary diary, String imgUrl) {
        return Image.builder()
                .url(imgUrl)
                .diary(diary)
                .build();
    }
}
