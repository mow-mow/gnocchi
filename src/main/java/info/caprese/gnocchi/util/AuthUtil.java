package info.caprese.gnocchi.util;

import info.caprese.gnocchi.data.SocialUserData;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class AuthUtil {
    public static String fetchUsedrId(Principal principal) {
        SocialUserData loginUser = (SocialUserData) ((Authentication)principal).getPrincipal();
        return loginUser.getUser().getUserId();
    }
}
