package at.ac.fernfh.sechsm.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.ac.fernfh.sechsm.registration.model.RegisteredUser;

public interface UserRepository extends JpaRepository<RegisteredUser, Long>{

	public List<RegisteredUser> findByPulbicKeyHash(String pulbicKeyHash);
}
