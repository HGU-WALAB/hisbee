package com.hcu.hot6.domain.converter;

import com.hcu.hot6.domain.enums.PostTypeDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PostTypeDetailsConverter implements Converter<String, PostTypeDetails> {

    @Override
    public PostTypeDetails convert(String source) {
        return Arrays.stream(PostTypeDetails.values())
                .filter(typeDetails -> typeDetails
                        .name().equals(source.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
