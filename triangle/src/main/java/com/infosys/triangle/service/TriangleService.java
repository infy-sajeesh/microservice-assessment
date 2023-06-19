package com.infosys.triangle.service;

import com.infosys.triangle.enumeration.ErrorCode;
import com.infosys.triangle.exception.InvalidDataException;
import com.infosys.triangle.utility.Constants;
import org.springframework.stereotype.Service;

@Service
public class TriangleService {

    public String checkTriangleType(String a, String b, String c){
        int i, j, k;
        try {
            i = Integer.parseInt(a);
            j = Integer.parseInt(b);
            k = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            throw new InvalidDataException(ErrorCode.INVALID_DATA, "Numbers only allowed");
        }
        if(i == j && j == k)
            return Constants.EQ;
        else if(i == j || j == k || i == k)
            return Constants.IS;
        else
            return Constants.SC;
    }
}
