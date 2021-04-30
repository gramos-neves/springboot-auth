package br.com.exemplo.ldap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private String username;
	    private String password;
	    private String roles;
	    private boolean active;
	    

	
}
