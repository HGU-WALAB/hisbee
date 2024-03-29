package com.hcu.hot6.domain.response;

import com.hcu.hot6.util.Utils;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreationResponse {

    @Builder
    public PostCreationResponse(Long id, String title, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.createdDate = Utils.toDate(createdDate);
    }

    private final Long id;
    private final String title;
    private final Date createdDate;
}
