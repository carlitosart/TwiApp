package bo.ucbx.twiapp.dao;

import bo.ucbx.twiapp.entities.TweetsEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetsRepository extends JpaRepository<TweetsEntity, Integer> {

    @Cacheable("timeline")
    @Query(
            value = "SELECT t.*\n" +
                    "FROM tweets t\n" +
                    "         JOIN follows f ON (t.user_id = f.user_followee_id)\n" +
                    "WHERE\n" +
                    "        f.user_follower_id = ?1\n" +
                    "ORDER BY t.tweet_id DESC\n" +
                    "LIMIT 20;",
            nativeQuery = true
    )
    public List<TweetsEntity> findTweetsForFollowers(int userId);


}