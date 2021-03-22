package info.caprese.gnocchi.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Service;

import info.caprese.gnocchi.data.LoginUserData;
import info.caprese.gnocchi.data.SocialUserData;
import info.caprese.gnocchi.service.spec.UserContext;

@Service
public class UserContextImpl implements UserContext {

	@Override
	public Optional<String> getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = null;
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			userId = ((LoginUserData) (auth.getPrincipal())).getUser().getUserId();
		} else if (auth instanceof SocialAuthenticationToken) {
			userId = ((SocialUserData) (auth.getPrincipal())).getUser().getUserId();
		}
		return Optional.ofNullable(userId);
	}

}
