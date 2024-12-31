package org.fastcampus.community_feed.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final User user = new User(1L, new UserInfo("name", "url"));
    private final User otherUser = new User(2L, new UserInfo("name", "url"));

    private final PostContent postContent = new PostContent("content");
    private final Post post = new Post(1L, user, postContent);

    @Test
    void givenCommentCreatedWhenLikeThenLikeCountShouldBe1() {
        // given
        Comment comment = new Comment(1L, post, user, postContent);
        User newUser = new User(2L, new UserInfo("name", "url"));

        // when
        comment.like(newUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        Comment comment = new Comment(1L, post, user, postContent);
        comment.getLikeCount();

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // given
        Comment comment = new Comment(1L, post, user, postContent);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenLikeBySameUserThenLikeCountShouldThrowError() {
        // given
        Comment comment = new Comment(1L, post, user, postContent);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }
}