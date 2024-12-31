package org.fastcampus.community_feed.post.repository.jpa;

import org.fastcampus.community_feed.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

}