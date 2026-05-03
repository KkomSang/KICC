package kicc.cicd.converter;

import kicc.cicd.domain.Diary;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import kicc.cicd.web.dto.diaryDTO.DiaryResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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
    public static DiaryResponseDTO.DiaryPreviewDTO toDiaryPreviewDTO(Diary diary) {
        return DiaryResponseDTO.DiaryPreviewDTO.builder()
                .diaryId(diary.getId())
                .title(diary.getTitle())
                .thumbnailUrl(diary.getThumbnailUrl())
                .createdAt(diary.getCreatedAt())
                .build();
    }

    public static DiaryResponseDTO.DiaryPreviewListDTO toDiaryPreviewListDTO(Page<Diary> diaryList) {
        List<DiaryResponseDTO.DiaryPreviewDTO> diaryPreviewDTOList = diaryList.stream()
                .map(diary -> toDiaryPreviewDTO(diary))
                .collect(Collectors.toList());
        return DiaryResponseDTO.DiaryPreviewListDTO.builder()
                .diaryList(diaryPreviewDTOList)
                .listSize(diaryList.getSize())
                .isFirst(diaryList.isFirst())
                .isLast(diaryList.isLast())
                .totalElements(diaryList.getTotalElements())
                .totalPage(diaryList.getTotalPages())
                .build();
    }
}
