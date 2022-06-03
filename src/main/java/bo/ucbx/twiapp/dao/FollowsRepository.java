package bo.ucbx.twiapp.dao;

import bo.ucbx.twiapp.entities.FollowsEntity;
import bo.ucbx.twiapp.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowsRepository extends JpaRepository<FollowsEntity, Integer> {


    // Sacamos todos los seguidores de un usuario
    @Query(
            value = "SELECT u " +
                    "FROM UsersEntity u " +
                    "    JOIN FollowsEntity f ON (u.userId = f.userFollowerId)\n" +
                    "WHERE\n" +
                    "    f.userFolloweeId = ?1"
    )
    public List<UsersEntity> findAllFollowersByUserId(int userId);
}