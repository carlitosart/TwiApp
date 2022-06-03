package bo.ucbx.twiapp.dao;

import bo.ucbx.twiapp.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
}
