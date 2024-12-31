package org.fastcampus.community_feed.user.application.interfaces;

import java.util.Optional;
import org.fastcampus.community_feed.user.domain.User;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}