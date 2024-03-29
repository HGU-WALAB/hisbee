package com.hcu.hot6.domain;

import static com.hcu.hot6.util.Utils.nonNullOrElse;
import static com.hcu.hot6.util.Utils.toLocalDateTime;

import com.hcu.hot6.domain.request.PostCreationRequest;
import com.hcu.hot6.domain.request.PostUpdateRequest;
import com.hcu.hot6.domain.response.PostThumbnailResponse;
import com.hcu.hot6.util.Utils;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id")
    private Long id;

    private String title;
    private String summary;
    private LocalDateTime recruitStart; // 미래인 경우 썸네일에 "모집 예정" , 아닌 경우 "D-00" 표시
    private LocalDateTime recruitEnd;

    private String duration;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "thumbnail")
    private Post post;

    private String tags; // 썸네일 내 키워드 - 줄 구분은 ";" 세미콜론, 키워드 구분은 "," 콤마

    private boolean isClosed; // 지원자가 상세보기 불가능한 상태 - 썸네일은 확인 가

    public void setPost(Post post) {
        this.post = post;
    }

    public Thumbnail(PostCreationRequest request) {
        // required
        this.title = request.getTitle();
        this.tags = request.getTags().combine();
        this.recruitStart = Utils.toLocalDateTime(request.getRecruitStart());

        // optional
        this.summary = (request.getSummary() != null) ? request.getSummary() : null;
        this.recruitEnd =
                (request.getRecruitEnd() != null) ? Utils.toLocalDateTime(request.getRecruitEnd()) : null;
        this.duration = (request.getDuration() != null) ? request.getDuration() : Duration.TBD.toKor();
    }

    public PostThumbnailResponse toResponse(String email) {
        return new PostThumbnailResponse(this, email);
    }

    public void update(PostUpdateRequest req) {
        this.title = nonNullOrElse(req.getTitle(), title);
        this.tags = (req.getTags() != null) ? req.getTags().combine() : tags;
        this.summary = nonNullOrElse(req.getSummary(), summary);
        this.recruitStart = nonNullOrElse(Utils.toLocalDateTime(req.getRecruitStart()), recruitStart);
        this.recruitEnd = toLocalDateTime(req.getRecruitEnd());
        this.duration = nonNullOrElse(req.getDuration(), duration);
        this.isClosed = nonNullOrElse(req.getIsClosed(), isClosed);

        if (req.getRecruitEnd() != null && req.getRecruitEnd().after(new Date())) this.isClosed = false;
    }

    public void close() {
        this.isClosed = true;
    }
}
