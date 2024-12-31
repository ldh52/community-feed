package org.fastcampus.community_feed.post.domain.comment;

import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.post.domain.like.LikeCounter;
import org.fastcampus.community_feed.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final LikeCounter likeCounter;

    public Comment(Long id, Post post, User author, Content content,
        LikeCounter likeCounter) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCounter = likeCounter;
    }

    public Comment(Long id, Post post, User author, Content content) {
        this(id, post, author, content, new LikeCounter());
    }

    public void updateContent(User user, String content) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("only author can update content");
        }
        this.content.updateContent(content);
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own comment");
        }
        likeCounter.increase();
    }

    public void unlike() {
        likeCounter.decrease();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
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