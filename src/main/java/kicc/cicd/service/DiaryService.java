package kicc.cicd.service;

import kicc.cicd.converter.DiaryConverter;
import kicc.cicd.domain.Diary;
import kicc.cicd.repository.DiaryRepository;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import kicc.cicd.web.dto.diaryDTO.DiaryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public DiaryResponseDTO.DiaryIdDTO createDiary(DiaryRequestDTO.DiaryDTO request) {
        Diary diary = DiaryConverter.toDiary(request);
        diaryRepository.save(diary);
        DiaryResponseDTO.DiaryIdDTO response = DiaryConverter.toDiaryIdDTO(diary);
        return response;
    }
}
