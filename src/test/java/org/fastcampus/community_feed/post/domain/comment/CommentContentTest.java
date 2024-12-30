package org.fastcampus.community_feed.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.community_feed.post.domain.content.CommentContent;
import org.junit.jupiter.api.Test;

class CommentContentTest {
    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenNotThrowError() {
        // given
        String content = "this is a test content";

        // when, then
        assertDoesNotThrow(() -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitCreatePostContentThenThrowError() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitAndKoreanCreatePostContentThenThrowError() {
        // given
        String content = "뷁".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsUnderLimitCreatePostContentThenNotThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertDoesNotThrow(() -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsEmptyLimitCreatePostContentThenThrowError() {
        // given
        String content = "";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsNullLimitCreatePostContentThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(null));
    }
}
