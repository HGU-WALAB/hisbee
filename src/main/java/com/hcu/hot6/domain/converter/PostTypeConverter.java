package com.hcu.hot6.domain.converter;

import com.hcu.hot6.domain.enums.PostType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PostTypeConverter implements Converter<String, PostType> {

    @Override
    public PostType convert(String source) {
        return Arrays.stream(PostType.values())
                .filter(type -> type
                        .name().equals(source.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new                                                                                               );
    }
}
