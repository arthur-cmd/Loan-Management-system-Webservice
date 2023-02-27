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
    Double score;
    Double limitAmount;
    String exclusion;
    String exclusionReason;

}


