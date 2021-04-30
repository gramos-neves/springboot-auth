package br.com.exemplo.ldap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.ldap.model.User;

public interface UserRepository extends  JpaRepository<User, Integer> {

	 Optional<User> findByUsername(String username);
	
}
