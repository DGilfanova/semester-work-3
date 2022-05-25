package ru.kpfu.itis.hauss.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.hauss.dto.IdeaDto;
import ru.kpfu.itis.hauss.exceptions.AccessException;
import ru.kpfu.itis.hauss.models.Status;
import ru.kpfu.itis.hauss.security.details.AccountUserDetails;
import ru.kpfu.itis.hauss.services.IdeasService;

@RequiredArgsConstructor
@Controller
public class IdeasController {

    private final IdeasService ideasService;

    private static final Logger logger = LoggerFactory.getLogger(IdeasController.class);

    @GetMapping("/ideas")
    public String getIdeasPage(@RequestParam(name = "page", required = false) Integer page, Model model) {
        model.addAttribute("ideasPage", ideasService.getPublicIdeas(page));
        return "ideas";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/ideas/{id}")
    public String getIdeasPage(@PathVariable("id") Long ideaId, Model model,
                                            @AuthenticationPrincipal AccountUserDetails userDetails) {
        IdeaDto ideaDto = ideasService.getIdeaById(ideaId);
        if (ideaDto.getStatus().equals(Status.PUBLIC) ||
                    ideaDto.getAccount().getId().equals(userDetails.getAccount().getId())) {
            model.addAttribute("idea", ideaDto);
            return "idea";
        } else {
            return "redirect:/ideas?denyAccess";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/ideas/favorites")
    public String addToFavorites(@AuthenticationPrincipal AccountUserDetails userDetails,
                                 @RequestParam("idea") Long ideaId) {
        ideasService.addIdeaToFavorite(userDetails.getAccount().getId(), ideaId);
        return "redirect:/profile/ideas/favorites";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/ideas/{id}/delete")
    public String getIdeasAfterDeletion(@PathVariable("id") Long ideaId,
                                        @AuthenticationPrincipal AccountUserDetails userDetails) {
        try {
            ideasService.deleteUserIdea(ideaId, userDetails.getAccount().getId());
            return "redirect:/profile";
        } catch (AccessException e) {
            logger.warn(userDetails.getAccount().getEmail() + " tried to delete idea that was forbidden to him");

            return "redirect:/ideas?denyAccess";
        }
    }
}

