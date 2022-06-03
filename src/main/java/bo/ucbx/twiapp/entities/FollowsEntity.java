package bo.ucbx.twiapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "follows", schema = "twiapp", catalog = "")
public class FollowsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "follow_id", nullable = false)
    private int followId;
    @Basic
    @Column(name = "user_follower_id", nullable = false)
    private int userFollowerId;
    @Basic
    @Column(name = "user_followee_id", nullable = false)
    private int userFolloweeId;

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getUserFollowerId() {
        return userFollowerId;
    }

    public void setUserFollowerId(int userFollowerId) {
        this.userFollowerId = userFollowerId;
    }

    public int getUserFolloweeId() {
        return userFolloweeId;
    }

    public void setUserFolloweeId(int userFolloweeId) {
        this.userFolloweeId = userFolloweeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowsEntity that = (FollowsEntity) o;
        return followId == that.followId && userFollowerId == that.userFollowerId && userFolloweeId == that.userFolloweeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, userFollowerId, userFolloweeId);
    }
}
