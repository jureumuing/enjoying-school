package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankEntity {
    private int rankId;
    private String rankName;
    private int condition;
}
