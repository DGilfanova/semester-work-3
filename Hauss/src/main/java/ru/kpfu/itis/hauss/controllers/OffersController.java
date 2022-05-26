package ru.kpfu.itis.hauss.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.hauss.dto.ApiResponse;
import ru.kpfu.itis.hauss.dto.OfferCreationDto;
import ru.kpfu.itis.hauss.dto.OfferDto;
import ru.kpfu.itis.hauss.exceptions.AccessException;
import ru.kpfu.itis.hauss.security.details.AccountUserDetails;
import ru.kpfu.itis.hauss.services.AllCategoriesService;
import ru.kpfu.itis.hauss.services.OffersService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OffersController {

    private final OffersService offersService;
    private final AllCategoriesService categoriesService;

    private static final Logger logger = LoggerFactory.getLogger(OffersController.class);

    @GetMapping("/offers")
    public String getOffersPage(Model model, @RequestParam(name = "page", required = false) Integer page) {
        model.addAttribute("offersPage", offersService.getPublicOffers(page));
        model.addAttribute("categories", categoriesService.getAllOfferCategories());
        return "offers";
    }

    @ResponseBody
    @GetMapping("/offers/filter-category")
    public ResponseEntity<ApiResponse> getFilterCategoryOffersPage(@RequestParam("category") Long categoryId) {
        //Для демонстрации гифки загрузки
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(offersService.searchByCategory(categoryId))
                        .build());
    }

    @ResponseBody
    @GetMapping("/offers/filter-title")
    public ResponseEntity<ApiResponse> getFilterTitleOffersPage(@RequestParam("title") String title) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(offersService.searchByTitle(title))
                        .build());
    }

    @GetMapping("/offers/{id}")
    public String getOffersPage(Model model, @PathVariable("id") Long offerId) {
        OfferDto offer = offersService.getOfferById(offerId);
        model.addAttribute("offer", offer);
        return "offer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/offers/{id}/deletion")
    public String getOffersAfterDeletion(@PathVariable("id") Long offerId,
                                         @AuthenticationPrincipal AccountUserDetails userDetails) {
        try {
            offersService.deleteUserOffer(offerId, userDetails.getAccount().getId());
            return "redirect:/profile/offers";
        } catch (AccessException e) {
            logger.warn(userDetails.getAccount().getEmail() + " tried to delete offer that was forbidden to him");

            return "redirect:/profile/offers?access";
        }
    }

    @GetMapping("/offers/{id}/edition")
    public String getEditOfferPage(Model model, @PathVariable("id") Long offerId) {
        model.addAttribute("offerCreationDto", offersService.getOfferById(offerId));
        model.addAttribute("categories", categoriesService.getAllOfferCategories());
        return "editOffer";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/offers/{id}/edition")
    public String editOffer(@Valid @ModelAttribute("offerCreationDto") OfferCreationDto offerCreationDto,
                            BindingResult result, @PathVariable("id") Long offerId,
                            Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("offerCreationDto", offerCreationDto);
            model.addAttribute("categories", categoriesService.getAllOfferCategories());
            return "createOffer";
        } else {
            try {
                offersService.editOffer(offerId, offerCreationDto, userDetails.getAccount().getId());
                return "redirect:/profile/offers?edit";
            } catch (AccessException e) {
                logger.warn(userDetails.getAccount().getEmail() + " tried to edit offer that was forbidden to him");

                return "redirect:/profile/offers?access";
            }
        }
    }
}

