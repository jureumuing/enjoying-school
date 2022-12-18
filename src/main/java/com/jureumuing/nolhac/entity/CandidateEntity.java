package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateEntity {
    private int candidateId;
    private String title;
    private String when;
    private String where;
    private String what;
    private String how;
    private int admitCount;
    private int userId;
}
