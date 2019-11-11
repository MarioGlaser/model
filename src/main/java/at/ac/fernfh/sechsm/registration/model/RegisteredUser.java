package at.ac.fernfh.sechsm.registration.model;

import java.security.PublicKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data 
public class RegisteredUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (length = 4096)
    @NotNull
    private String pulbicKeyValue;
    
    @JsonIgnore
    @Transient
    private PublicKey publicKey;
    
    
    @Column (length = 128) 
    private String userName;
    
    
    @Column (length = 2048)
    private String idToken;
    
    
}
