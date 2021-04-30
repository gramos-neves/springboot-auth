package br.com.exemplo.ldap.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.exemplo.ldap.model.MyUserDetails;
import br.com.exemplo.ldap.model.User;
import br.com.exemplo.ldap.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

		/*
		 * 
		 * if(user == null) { throw new UsernameNotFoundException(username); }
		 */

		System.out.println(user.toString());

		return user.map(MyUserDetails::new).get();
	}

}
