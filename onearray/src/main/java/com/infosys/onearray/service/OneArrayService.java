package com.infosys.onearray.service;

import com.infosys.onearray.dto.OneArrayDto;
import com.infosys.onearray.dto.ThreeArrayDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
@Service
public class OneArrayService {

    public OneArrayDto getOneArray(ThreeArrayDto threeArrayDto){
        OneArrayDto oneArrayDto = new OneArrayDto();
        List<Integer> singleArray;
        List<Integer> integerList = Stream.of(threeArrayDto.getArray1(), threeArrayDto.getArray2(), threeArrayDto.getArray3())
                .flatMap(Collection::stream)
                .toList();
        singleArray = integerList.stream().distinct().sorted().toList();
        oneArrayDto.setArray(singleArray);
        return oneArrayDto;
    }
}
