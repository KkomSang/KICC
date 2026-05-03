package kicc.cicd.web.dto.diaryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class DiaryResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryIdDTO {
        Long diaryId;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryPreviewListDTO {
        List<DiaryPreviewDTO> diaryList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryPreviewDTO {
        Long diaryId;
        String title;
        String thumbnailUrl;
        LocalDateTime createdAt;
    }
}
