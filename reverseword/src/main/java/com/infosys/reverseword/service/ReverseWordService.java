package com.infosys.reverseword.service;

import com.infosys.reverseword.enumeration.ErrorCode;
import com.infosys.reverseword.exception.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public class ReverseWordService {

    public String reverseWords(String words){

        if(words.isBlank() || words.isEmpty()){
            throw new InvalidDataException(ErrorCode.INVALID_DATA, "Words cannot be null or blank");
        }
        String eachWords[] = words.split("\\s");
        String reverseWord = "";
        for(String word:eachWords){
            StringBuilder sb=new StringBuilder(word);
            sb.reverse();
            reverseWord+=sb.toString()+" ";
        }
        return reverseWord;
    }
}
