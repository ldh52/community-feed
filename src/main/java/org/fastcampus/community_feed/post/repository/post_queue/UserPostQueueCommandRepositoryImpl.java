package org.fastcampus.community_feed.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.fastcampus.community_feed.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    public void publishPost(PostEntity postEntity) {
        UserEntity authorEntity = postEntity.getAuthor();
        List<Long> followers = jpaUserRelationRepository.findFollowers(authorEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followers.stream()
            .map(
                userId -> new UserPostQueueEntity(postEntity.getId(), userId, authorEntity.getId()))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
            .map(postId -> new UserPostQueueEntity(postId, userId, targetId))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}