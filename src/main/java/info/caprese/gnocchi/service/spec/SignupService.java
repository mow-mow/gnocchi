package info.caprese.gnocchi.service.spec;

import org.springframework.social.connect.UserProfile;

import info.caprese.gnocchi.entity.User;

public interface SignupService {

	User createUser(UserProfile userProfile);

}
