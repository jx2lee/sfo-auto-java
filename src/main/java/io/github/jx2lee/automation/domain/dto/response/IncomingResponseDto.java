package io.github.jx2lee.automation.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncomingResponseDto {
    private String resultMessage;
    private boolean isSuccessful;
    private Integer resultCode;
}
