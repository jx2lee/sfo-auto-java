package com.jx2lee.sfo.api;

import com.jx2lee.sfo.domain.dto.PostReceiveDto;
import com.jx2lee.sfo.domain.dto.response.IncomingResponseDto;
import com.jx2lee.sfo.service.NotifyIncomingPostService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class IncomingApiController {

    private final NotifyIncomingPostService service;

    @SneakyThrows
    @PostMapping("/posts")
    public IncomingResponseDto renameTitleV1(@RequestBody PostReceiveDto postReceiveDto) {
        String postId = postReceiveDto.getPost().id;
        String postContent = postReceiveDto.getPost().body.content;

        if (service.isEqualSubject(postReceiveDto.getPost().subject)) {
            String renamedSubject = service.searchSubject(postContent);

            log.info("subject={}, postId={}", renamedSubject, postId);
            service.putSubject(renamedSubject, postId);

            return IncomingResponseDto.builder()
                    .isSuccessful(true)
                    .resultCode(HttpStatus.OK.value())
                    .resultMessage("SUCCEED")
                    .build();
        }

        return IncomingResponseDto.builder()
                .isSuccessful(false)
                .resultCode(HttpStatus.BAD_REQUEST.value())
                .resultMessage("FAILED")
                .build();
    }
}
