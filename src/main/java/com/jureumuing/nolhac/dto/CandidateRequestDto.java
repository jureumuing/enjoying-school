package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CandidateRequestDto {
    String title;
    String when;
    String where;
    String how;
    String what;
    MultipartFile exampleImage;
    int user_id;
}
