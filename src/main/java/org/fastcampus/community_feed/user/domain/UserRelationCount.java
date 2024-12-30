package org.fastcampus.community_feed.user.domain;

public class UserRelationCount {
  private int count;

  public UserRelationCount() {
    this.count = 0;
  }

  public void increaseCount() {
    this.count++;
  }

  public void decreaseCount() {
    if (count > 0) {
      count--;
    }
  }

  public int getCount() {
    return count;
  }
}
