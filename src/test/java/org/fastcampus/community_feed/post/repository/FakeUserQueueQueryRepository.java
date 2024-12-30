package org.fastcampus.community_feed.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserQueueQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRepository queueRepository;

    public FakeUserQueueQueryRepository(FakeUserQueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        List<PostEntity> postEntities = queueRepository.getPostsByUserId(userId);

        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto content = GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .commentCount(postEntity.getCommentCounter())
                .build();
            result.add(content);
        }
        return result;
    }
}
