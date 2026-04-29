package kicc.cicd.converter;

import kicc.cicd.domain.Diary;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import kicc.cicd.web.dto.diaryDTO.DiaryResponseDTO;

public class DiaryConverter {
    public static Diary toDiary(DiaryRequestDTO.DiaryDTO request) {
        return Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .thumbnailUrl(null) //todo
                .imageList(null)    //todo
                .build();
    }
    public static DiaryResponseDTO.DiaryIdDTO toDiaryIdDTO(Diary request) {
        return DiaryResponseDTO.DiaryIdDTO.builder()
                .diaryId(request.getId())
                .build();
    }
}
