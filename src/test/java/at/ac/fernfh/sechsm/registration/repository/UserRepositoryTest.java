package at.ac.fernfh.sechsm.registration.repository;

import static org.junit.Assert.assertTrue;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.List;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.fernfh.sechsm.SecHSMUtil;
import at.ac.fernfh.sechsm.registration.model.RegisteredUser;

@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
   @Test
   public void searchPublicKeyWithHash() throws Exception {
	   
		KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance("RSA");
		kpGenerator.initialize(2048);
		KeyPair keyPair = kpGenerator.genKeyPair();

		PublicKey publicKey = keyPair.getPublic();

		RegisteredUser registration = new RegisteredUser();
		registration.setEMail("JUnit-" + System.nanoTime() + "@mail.com");
		String publicKeyHash = Hex.toHexString(SecHSMUtil.getPublicKeyHash(publicKey));
		registration.setPulbicKeyHash(publicKeyHash);
		registration.setPulbicKeyValue(Hex.toHexString(publicKey.getEncoded()));
		entityManager.persist(registration);
		entityManager.flush();
		
		List<RegisteredUser> findByPulbicKeyHash = userRepository.findByPulbicKeyHash(publicKeyHash);
		
		assertTrue(findByPulbicKeyHash.size() == 1);
		
   }
}
