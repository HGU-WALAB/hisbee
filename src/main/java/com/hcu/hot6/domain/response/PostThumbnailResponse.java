package com.hcu.hot6.domain.response;

import com.hcu.hot6.domain.Thumbnail;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import static com.hcu.hot6.util.Utils.toArray;
import static com.hcu.hot6.util.Utils.toDate;

@Getter
public class PostThumbnailResponse {
    private Long id;
    private String title;
    private String summary;
    private Date recruitStart;
    private Date recruitEnd;
    private List<String> postTypes;
    private List<String> tags;
    private Long views;
    private boolean hasLiked;
    private Date createdDate;

    public PostThumbnailResponse(final Thumbnail thumbnail, String email) {
        this.id = thumbnail.getPost().getId();
        this.title = thumbnail.getTitle();
        this.summary = thumbnail.getSummary();
        this.postTypes = toArray(thumbnail.getPost().getPostTypes(), ",");
        this.tags = toArray(thumbnail.getTags(), ";");
        this.views = thumbnail.getPost().getViews();
        this.createdDate = toDate(thumbnail.getPost().getCreatedDate());
        this.recruitStart = toDate(thumbnail.getRecruitStart());
        this.recruitEnd = toDate(thumbnail.getRecruitEnd());
        this.hasLiked = thumbnail.getPost().getBookmarks().stream()
                .anyMatch(bookmark -> email.equals(bookmark.getMember().getEmail()));
    }
}