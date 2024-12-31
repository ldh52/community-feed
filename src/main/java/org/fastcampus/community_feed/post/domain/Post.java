package org.fastcampus.community_feed.post.domain;

import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.post.domain.like.LikeCounter;
import org.fastcampus.community_feed.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private PostPublicationState state;
    private final LikeCounter likeCounter;

    public Post(Long id, User author, Content content, PostPublicationState state,
        LikeCounter likeCounter) {
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.state = state;
        this.likeCounter = likeCounter;
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new LikeCounter());
    }

    public void updateContent(User user, String content) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("only author can update content");
        }
        this.content.updateContent(content);
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own post");
        }
        likeCounter.increase();
    }

    public void unlike() {
        likeCounter.decrease();
    }

    public void updateState(PostPublicationState state) {
        this.state = state;
    }

    public PostPublicationState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCounter.getLikeCount();
    }
}