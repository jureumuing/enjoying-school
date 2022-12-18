package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdmitEntity {
    private int admitId;
    private int userId;
    private int candidateId;
}
