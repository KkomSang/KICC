package kicc.cicd.web.controller;

import kicc.cicd.api.ApiResponse;
import kicc.cicd.api.code.status.SuccessStatus;
import kicc.cicd.service.DiaryService;
import kicc.cicd.web.dto.diaryDTO.DiaryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping
    public ApiResponse createDiary(@RequestBody DiaryRequestDTO.DiaryDTO request) {
        return ApiResponse.of(SuccessStatus._DIARY_CREATE_OK, diaryService.createDiary(request));
    }
}
