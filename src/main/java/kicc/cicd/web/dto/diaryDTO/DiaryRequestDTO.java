package kicc.cicd.web.dto.diaryDTO;

import lombok.Getter;

public class DiaryRequestDTO {
    @Getter
    public static class DiaryDTO {
        String title;
        String content;
    }
}
