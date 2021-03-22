package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.*;
import info.caprese.gnocchi.repository.GourmetRepository;
import info.caprese.gnocchi.repository.ScheduleRepository;
import info.caprese.gnocchi.repository.TweetJournalRepository;
import info.caprese.gnocchi.repository.UserRepository;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
public class TweetMessageLogic {

    public TweetMessageLogic() {

        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        this.templateEngine = templateEngine;
    }
    private SpringTemplateEngine templateEngine;

    @Autowired
    UserRepository userRepository;

    public String renderTemplate(String template, Gourmet gourmet) {
        Context context = new Context();

        context.setVariable("comment", gourmet.getComment());
        context.setVariable("menu", gourmet.getMenu());
        context.setVariable("shopName", gourmet.getShopName());

        Optional<User> user = userRepository.findOne(gourmet.getUserId());
        context.setVariable("literatureDisplayFlag", gourmet.isLiteratureDisplayFlag());
        context.setVariable("displayName", user.get().getDisplayName());

        return templateEngine.process(template, context);
    }

}





