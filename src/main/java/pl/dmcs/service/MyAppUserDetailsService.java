package pl.dmcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.utils.MyUserDetails;

import java.util.*;

@Service("myAppUserDetailsService")
public class MyAppUserDetailsService implements UserDetailsService {

	private AppUserService appUserService;

	@Autowired
	public MyAppUserDetailsService(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
 
		pl.dmcs.domain.AppUser appUser = appUserService.findByLogin(login);
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(appUser.getRole()));
		return buildUserForAuthentication(appUser, authorities, appUser.getId());
	}
 
	// Converts AppUser user to org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(pl.dmcs.domain.AppUser appUser, List<GrantedAuthority> authorities, long userId) {

		return new MyUserDetails(appUser.getLogin(), appUser.getPassword(), appUser.isEnabled(),
				true, true, true, authorities, userId);
	}
}

