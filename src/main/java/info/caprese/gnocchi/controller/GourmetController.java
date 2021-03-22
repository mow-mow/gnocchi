package info.caprese.gnocchi.controller;

import info.caprese.gnocchi.data.LoginUserData;
import info.caprese.gnocchi.data.SocialUserData;
import info.caprese.gnocchi.entity.Gourmet;
import info.caprese.gnocchi.entity.ScheduleTemplate;
import info.caprese.gnocchi.repository.GourmetRepository;
import info.caprese.gnocchi.repository.ScheduleRepository;
import info.caprese.gnocchi.repository.ScheduleTemplateRepository;
import info.caprese.gnocchi.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
public class GourmetController {

	@Autowired
	private GourmetRepository gourmetRepository;

	@Autowired
	private ScheduleTemplateRepository scheduleTemplateRepository;


	@GetMapping(path = "/user/{botId}/gourmets")
	String gourmets(Model model, Principal principal, @PathVariable("botId") String botId) {
		List<Gourmet> result = gourmetRepository.findByBotIdAndUserIdAndDeleteFlagOrderByInsertDateDesc(botId, AuthUtil.fetchUsedrId(principal), false);
		model.addAttribute("gourmets", result);
		return "user/gourmets";
	}

	@GetMapping(path = "/user/{botId}/gourmets/new")
	String gourmetsNew(Model model, @PathVariable("botId") String botId) {

		List<ScheduleTemplate> r = scheduleTemplateRepository.findByBotIdAndInvalidFlagAndDeleteFlag(botId, false, false);
		Gourmet gourmet = new Gourmet();
		gourmet.setBotId(botId);
		model.addAttribute("scheduleTemplates", r);

		model.addAttribute("mode", "new");
		model.addAttribute("gourmet", gourmet);
		return "user/form";
	}

	@GetMapping(path = "/user/{botId}/gourmets/{gourmetId}/edit")
	String edit(Model model, @PathVariable("gourmetId") String gourmetId, @PathVariable("botId") String botId, Principal principal) throws IllegalAccessException {
		Optional<Gourmet> result = gourmetRepository.findByBotIdAndUserIdAndGourmetIdAndDeleteFlag(botId, AuthUtil.fetchUsedrId(principal), gourmetId, false);
		if (!result.isPresent()) {
			throw new IllegalAccessException();
		}
		model.addAttribute("gourmet", result.get());
		return "user/form";
	}

	@PostMapping(path = "/user/{botId}/gourmets")
	String regist(@PathVariable("botId") String botId, Principal principal, Gourmet gourmet, RedirectAttributes redirectAttributes) {

		gourmet.setBotId(botId);
		UUID uuid = UUID.randomUUID();
		gourmet.setGourmetId(uuid.toString());

		gourmet.setUserId(AuthUtil.fetchUsedrId(principal));
		gourmet.setInsertDate(LocalDateTime.now());
		gourmet.setUpdateDate(LocalDateTime.now());

		redirectAttributes.addFlashAttribute("registSuccusessFlag", true);
		gourmetRepository.save(gourmet);

		return "redirect:/user/" + botId + "/gourmets";
	}
	@GetMapping(path = "/user/{botId}/gourmets/{gourmetId}")
	String detail(Model model, @PathVariable("gourmetId") String gourmetId, @PathVariable("botId") String botId, Principal principal) throws IllegalAccessException {
		Optional<Gourmet> result = gourmetRepository.findByBotIdAndUserIdAndGourmetIdAndDeleteFlag(botId, AuthUtil.fetchUsedrId(principal), gourmetId, false);

		if (!result.isPresent()) {
			throw new IllegalAccessException();
		}
		model.addAttribute("gourmet", result.get());
		return "user/detail";
	}



	@PutMapping(path = "/user/{botId}/gourmets/{gourmetId}")
	String update(Principal principal, Gourmet gourmetForm, @PathVariable("botId") String botId, @PathVariable("gourmetId") String gourmetId, RedirectAttributes redirectAttributes) throws IllegalAccessException {

		Optional<Gourmet> gourmet = gourmetRepository.findByBotIdAndUserIdAndGourmetIdAndDeleteFlag(botId, AuthUtil.fetchUsedrId(principal), gourmetId, false);
		if (!gourmet.isPresent()) {
			throw new IllegalAccessException();
		}

		Gourmet entity = gourmet.get();

		entity.setMenu(gourmetForm.getMenu());
		entity.setShopName(gourmetForm.getShopName());
		entity.setComment(gourmetForm.getComment());
		entity.setInvalidFlag(gourmetForm.isInvalidFlag());
		entity.setLiteratureDisplayFlag(gourmetForm.isLiteratureDisplayFlag());

		entity.setUpdateDate(LocalDateTime.now());

		gourmetRepository.save(entity);

		redirectAttributes.addFlashAttribute("updateSuccusessFlag", true);


		return "redirect:/user/" + botId + "/gourmets";
	}

	@DeleteMapping(path = "/user/{botId}/gourmets/{gourmetId}")
	String delete(@PathVariable("botId") String botId, Principal principal, @PathVariable("gourmetId") String gourmetId, RedirectAttributes redirectAttributes) throws IllegalAccessException {
		Optional<Gourmet> gourmet = gourmetRepository.findByBotIdAndUserIdAndGourmetIdAndDeleteFlag(botId, AuthUtil.fetchUsedrId(principal), gourmetId, false);
		if (!gourmet.isPresent()) {
			throw new IllegalAccessException();
		}

		Gourmet entity = gourmet.get();
		entity.setDeleteFlag(true);
		entity.setUpdateDate(LocalDateTime.now());

		gourmetRepository.save(entity);

		redirectAttributes.addFlashAttribute("deleteSuccusessFlag", true);

		return "redirect:/user/" + botId + "/gourmets";
	}
}