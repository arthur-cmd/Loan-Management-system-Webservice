package io.credable.loanmanagementsystem.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ScoringDTO {
    int  id;
    String CustomerNumber;
    int score;
    int limitAmount;
    String exclusion;
    String exclusionReason;

}


