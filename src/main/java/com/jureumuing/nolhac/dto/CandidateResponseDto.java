package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateResponseDto {
    int candidateId;
    int admitCount;
    String title;
    String when;
    String where;
    String how;
    String what;
    String writer;
}
