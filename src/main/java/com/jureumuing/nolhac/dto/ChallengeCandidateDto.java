package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeCandidateDto {
    String title;
    String when;
    String where;
    String how;
    String what;
    int user_id;
}
