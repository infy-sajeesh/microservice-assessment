package com.infosys.onearray.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ThreeArrayDto {

    @NotNull
    private List<Integer> array1;
    @NotNull
    private List<Integer> array2;
    @NotNull
    private List<Integer> array3;
}
