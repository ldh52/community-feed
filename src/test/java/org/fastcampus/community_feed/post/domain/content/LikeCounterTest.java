package org.fastcampus.community_feed.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.community_feed.post.domain.like.LikeCounter;
import org.junit.jupiter.api.Test;



class LikeCounterTest {

    @Test
    void givenCreatedWhenIncreaseThenCountIsOne() {
        // given
        LikeCounter count = new LikeCounter();

        // when
        count.increase();

        // then
        assertEquals(1, count.getLikeCount());
    }

    @Test
    void givenCreatedAndLikedWhenDecreaseThenCountIsOne() {
        // given
        LikeCounter count = new LikeCounter();
        count.increase();

        // when
        count.decrease();

        // then
        assertEquals(0, count.getLikeCount());
    }

    @Test
    void givenCreatedWhenDecreaseThenCountIsZero() {
        // given
        LikeCounter count = new LikeCounter();

        // when
        count.decrease();

        // then
        assertEquals(0, count.getLikeCount());
    }

}
