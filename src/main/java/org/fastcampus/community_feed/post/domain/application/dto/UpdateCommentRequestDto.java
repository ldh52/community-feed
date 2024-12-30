package org.fastcampus.community_feed.post.domain.application.dto;

public record UpdateCommentRequestDto(
    Long commentId,
    Long authorId,
    String content
) {

}
