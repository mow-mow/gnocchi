package info.caprese.gnocchi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import info.caprese.gnocchi.entity.GourmetSumarry;
import info.caprese.gnocchi.repository.GourmetRepository;
import info.caprese.gnocchi.repository.GourmetSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	HttpSession session;

	@Autowired
	private GourmetSummaryRepository gourmetSummaryRepository;
	@Autowired
	private Twitter twitter;

	@GetMapping(path = "/")
	String showMainPage(Model model) {
		writeTweetIdIfLoggedInWithTwitter();

		List<GourmetSumarry> result = gourmetSummaryRepository.find();

		model.addAttribute("result", result);
		return "main/main";
	}

	private void writeTweetIdIfLoggedInWithTwitter() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof SocialAuthenticationToken) {
			long tweetId = twitter.timelineOperations().getUserTimeline().get(0).getId();
			TwitterProfile profile = twitter.userOperations().getUserProfile();

			session.setAttribute("icon", profile.getProfileImageUrl());
			log.info(""+profile.getProfileImageUrl());
			log.info(""+tweetId);

	//		 twitter.timelineOperations().updateStatus("Hello!!");
		}
	}

}