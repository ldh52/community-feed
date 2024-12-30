package org.fastcampus.community_feed.post.domain.application.interfaces;

import java.util.Optional;
import org.fastcampus.community_feed.post.domain.Post;

public interface PostRepository {

    Optional<Post> findById(Long id);

    Post save(Post post);
}
