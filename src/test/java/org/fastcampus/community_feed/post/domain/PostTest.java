package org.fastcampus.community_feed.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final User user = new User(1L, new UserInfo("name", "url"));
    private final User otherUser = new User(2L, new UserInfo("name", "url"));

    private final PostContent postContent = new PostContent("content");

    @Test
    void givenPostCreatedWhenLikeThenLikeCountShouldBe1() {
        // given
        Post post = new Post(1L, user, postContent);

        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        Post post = new Post(1L, user, postContent);
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // given
        Post post = new Post(1L, user, postContent);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenLikeByOtherUserThenThrowException() {
        // given
        Post post = new Post(1L, user, postContent);
        post.like(otherUser);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        Post post = new Post(1L, user, postContent);
        String newPostContent = "new content";

        // when
        post.updateContent(user, newPostContent);

        // then
        Content content = post.getContent();
        assertEquals(newPostContent, content.getContentText());
    }

    @Test
    void givenPostCreatedWhenUpdateContentByOtherUserThenThrowException() {
        // given
        Post post = new Post(1L, user, postContent);
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> post.updateContent(otherUser, newPostContent));
    }

    @Test
    void givenPostCreatedWhenUpdateStateThenStateShouldBeUpdated() {
        // given
        Post post = new Post(1L, user, postContent);
        PostPublicationState newPostState = PostPublicationState.PRIVATE;

        // when
        post.updateState(newPostState);

        // then
        assertEquals(newPostState, post.getState());
    }
}