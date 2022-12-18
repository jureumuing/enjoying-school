package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ChallengePostingReq {
    private String challengeTitle;
    private String when;
    private String where;
    private String what;
    private String how;
    private MultipartFile provingImage;
    private MultipartFile provingVideo;
}
