package at.ac.fernfh.sechsm.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import at.ac.fernfh.sechsm.registration.model.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long>{

	public List<RegisteredUser> findByPulbicKeyHash(String pulbicKeyHash);
}
