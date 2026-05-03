package kicc.cicd.web.controller;

import kicc.cicd.api.ApiResponse;
import kicc.cicd.api.code.status.SuccessStatus;
import kicc.cicd.service.DiaryService;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import kicc.cicd.web.dto.diaryDTO.DiaryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping
    public ApiResponse<DiaryResponseDTO.DiaryIdDTO> createDiary(@RequestBody DiaryRequestDTO.DiaryDTO request) {
        return ApiResponse.of(SuccessStatus._DIARY_CREATE_OK, diaryService.createDiary(request));
    }
    @GetMapping
    public ApiResponse<DiaryResponseDTO.DiaryPreviewListDTO> getDiaryList(@RequestParam(value = "page", defaultValue = "1")Integer page) {
        return ApiResponse.of(SuccessStatus._DIARY_LIST_OK, diaryService.getDiaryList(page));
    }
    @GetMapping("/{diaryId}")
    public ApiResponse<DiaryResponseDTO.DiaryDetailDTO> getDiary(@PathVariable Long diaryId) {
        return ApiResponse.of(SuccessStatus._DIARY_DETAIL_OK, diaryService.getDiary(diaryId));
    }
}
