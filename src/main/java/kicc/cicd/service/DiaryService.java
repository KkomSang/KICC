package kicc.cicd.service;

import jakarta.persistence.EntityNotFoundException;
import kicc.cicd.converter.DiaryConverter;
import kicc.cicd.converter.ImageConverter;
import kicc.cicd.domain.Diary;
import kicc.cicd.domain.Image;
import kicc.cicd.gcp.GCPStorageService;
import kicc.cicd.repository.DiaryRepository;
import kicc.cicd.repository.ImageRepository;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import kicc.cicd.web.dto.diaryDTO.DiaryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final ImageRepository imageRepository;
    private final GCPStorageService gcpStorageService;
    @Value("${spring.cloud.gcp.default-thumbnail}")
    private String default_thumbnail;
    public DiaryResponseDTO.DiaryIdDTO createDiary(DiaryRequestDTO.DiaryDTO request, List<MultipartFile> images) {
        String thumbnail = default_thumbnail;
        Diary diary = DiaryConverter.toDiary(request);
        diaryRepository.save(diary);
        if(images!=null && !images.isEmpty()) {
            for (int i = 0; i < images.size(); i++) {
                MultipartFile img = images.get(i);
                String imgUrl = gcpStorageService.uploadFile(img);
                Image image = ImageConverter.toImage(diary, imgUrl);
                imageRepository.save(image);
                if (i == 0) {
                    thumbnail = imgUrl;
                }
            }
        }
        diary.setThumbnailUrl(thumbnail);
        diaryRepository.save(diary);
        DiaryResponseDTO.DiaryIdDTO result = DiaryConverter.toDiaryIdDTO(diary);
        return result;
    }
    public DiaryResponseDTO.DiaryPreviewListDTO getDiaryList(Integer page) {
        PageRequest pageRequest = PageRequest.of(page-1, 4);
        DiaryResponseDTO.DiaryPreviewListDTO result = DiaryConverter.toDiaryPreviewListDTO(diaryRepository.findAll(pageRequest));
        return result;
    }
    public DiaryResponseDTO.DiaryDetailDTO getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(()-> new EntityNotFoundException("invalid diaryId"));
        List<String> imageUrlList = imageRepository.findAllUrlByDiary(diary);
        DiaryResponseDTO.DiaryDetailDTO result = DiaryConverter.toDiaryDetailDTO(diary, imageUrlList);
        return result;
    }

}
