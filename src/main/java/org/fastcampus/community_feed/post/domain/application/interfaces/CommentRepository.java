package org.fastcampus.community_feed.post.domain.application.interfaces;

import java.util.Optional;
import org.fastcampus.community_feed.post.domain.comment.Comment;

public interface CommentRepository {

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);
}
