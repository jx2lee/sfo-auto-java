package com.jx2lee.sfo.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncomingResponseDto {
    private String resultMessage;
    private boolean isSuccessful;
    private Integer resultCode;
}
