package bo.ucbx.twiapp.dao;

import bo.ucbx.twiapp.entities.FollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<FollowsEntity, Integer> {
}