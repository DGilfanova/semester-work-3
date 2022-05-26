package ru.kpfu.itis.hauss.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.dto.IdeaCreationDto;
import ru.kpfu.itis.hauss.dto.OfferCreationDto;
import ru.kpfu.itis.hauss.dto.OrderCreationDto;
import ru.kpfu.itis.hauss.exceptions.CreateOrderException;
import ru.kpfu.itis.hauss.dto.ChangeCurrencyDto;
import ru.kpfu.itis.hauss.security.details.AccountUserDetails;
import ru.kpfu.itis.hauss.services.*;
import ru.kpfu.itis.hauss.utils.api.currencies.CurrencyConverterException;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final AccountService accountService;
    private final IdeasService ideasService;
    private final OffersService offersService;
    private final OrdersService ordersService;
    private final AllCategoriesService categoriesService;
    private final CurrenciesService currenciesService;

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        model.addAttribute("account", accountService.getAccountWithIdeas(userDetails.getAccount().getId()));
        return "profile";
    }

    @GetMapping("/create-idea")
    public String getCreateIdeaPage(Model model) {
        model.addAttribute("ideaCreationDto", new IdeaCreationDto());
        model.addAttribute("categories", categoriesService.getAllIdeaCategories());
        model.addAttribute("styles", categoriesService.getAllStyles());
        return "createIdea";
    }

    @GetMapping("/create-offer")
    public String getCreateOfferPage(Model model) {
        model.addAttribute("offerCreationDto", new OfferCreationDto());
        model.addAttribute("categories", categoriesService.getAllOfferCategories());
        return "createOffer";
    }

    @GetMapping("/offers")
    public String getOffersPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails,
                                @RequestParam(name = "page", required = false) Integer page) {
        model.addAttribute("offersPage", offersService.getUserOffers(
                                                                        userDetails.getAccount().getId(), page));
        model.addAttribute("categories", categoriesService.getAllOfferCategories());
        return "profileOffers";
    }

    @GetMapping("/timetable/client")
    public String getNearestOrdersForClient(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("orders", ordersService.getNearestOrderedServices(
                userDetails.getAccount().getId()));
        return "clientTimetable";
    }

    @GetMapping("/timetable/prof")
    public String getNearestOrdersForProfessional(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("orders", ordersService.getNearestOrders(userDetails.getAccount().getId()));
        return "professionalTimetable";
    }

    @GetMapping("/ideas/favorites")
    public String getFavoritesPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("ideas", ideasService.getFavouritesIdeas(userDetails.getAccount().getId()));
        return "favorites";
    }

    @GetMapping("/statistics/orders-history")
    public String getUserOrders(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("orders", ordersService.getUserOrders(userDetails.getAccount().getId()));
        return "ordersHistory";
    }

    @GetMapping("/statistics/popular-offer")
    public String getStatisticsPopularOffersPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("offers",
                offersService.findMostPopularByOrdersOffers(userDetails.getAccount().getId()));
        return "statPopularOffers";
    }

    @GetMapping("/statistics/adv-offer")
    public String getStatisticsAdvOffersPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails,
                                             @RequestParam("category_id") Long categoryId) {
        model.addAttribute("offers",
                offersService.findUserMostAdvantageousOffersByCategory(userDetails.getAccount().getId(), categoryId));
        return "statAdvOffers";
    }

    @GetMapping("/statistics/last-ideas")
    public String getStatisticsLastIdeasPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        model.addAttribute("ideas", ideasService.getLastUserPublishedIdeas(userDetails.getAccount().getId()));
        return "statLastIdeas";
    }

    @PostMapping("/create-idea")
    public String addNewIdea(@Valid @ModelAttribute("ideaCreationDto") IdeaCreationDto ideaCreationDto,
                             BindingResult result, @AuthenticationPrincipal AccountUserDetails userDetails,
                             @RequestParam(name = "file", required = false) MultipartFile multipartFile, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ideaCreationDto", ideaCreationDto);
            model.addAttribute("categories", categoriesService.getAllIdeaCategories());
            model.addAttribute("styles", categoriesService.getAllStyles());
            return "createIdea";
        } else {
            ideasService.addUserIdea(ideaCreationDto, userDetails.getAccount().getId(), multipartFile);
            return "redirect:/profile?success";
        }
    }

    @PostMapping("/create-offer")
    public String addNewOffer(@Valid @ModelAttribute("offerCreationDto") OfferCreationDto offerCreationDto,
                             BindingResult result, @RequestParam(name = "file", required = false) MultipartFile multipartFile,
                             Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("offerCreationDto", offerCreationDto);
            model.addAttribute("categories", categoriesService.getAllOfferCategories());
            return "createOffer";
        } else {
            offersService.addUserOffer(offerCreationDto, userDetails.getAccount().getId(), multipartFile);
            return "redirect:/profile/offers?success";
        }
    }

    @GetMapping("/create-order")
    public String getCreateOrderPage(Model model, @RequestParam("offer") Long offerId,
                                     @RequestParam(value = "convert", required = false) String convertFlag,
                                     @ModelAttribute("changeCurrencyDto") ChangeCurrencyDto changeCurrencyDto,
                                     @RequestParam(value = "new_price", required = false) Double newPrice,
                                     @RequestParam(value = "new_cur_id", required = false) Long newCurrencyId,
                                     @RequestParam(value = "new_cur_name", required = false) String newCurrencyName) {
        model.addAttribute("changeCurrencyDto", new ChangeCurrencyDto());
        model.addAttribute("currencies", currenciesService.getAllCurrencies());
        if (convertFlag == null) {
            OrderCreationDto orderCreationDto = ordersService.prepareDefaultCreationForm(offerId);
            if (newPrice != null && newCurrencyId != null && newCurrencyName != null) {
                orderCreationDto.setTotalPrice(newPrice);
                orderCreationDto.setCurrencyId(newCurrencyId);
                orderCreationDto.setCurrencyName(newCurrencyName);
            }
            model.addAttribute("orderCreationDto", orderCreationDto);
            return "createOrder";
        } else {
            try {
                OrderCreationDto orderCreationDto = ordersService.getUserOrderWithNewCurrencies(changeCurrencyDto, offerId);
                ChangeCurrencyDto currencyDto = ChangeCurrencyDto.builder()
                        .newCurrencyId(orderCreationDto.getCurrencyId())
                        .totalPrice(orderCreationDto.getTotalPrice())
                        .build();
                model.addAttribute("orderCreationDto", orderCreationDto);
                String parameters = "offer=" + offerId + "&new_price=" + currencyDto.getTotalPrice() +
                    "&new_cur_id=" + changeCurrencyDto.getNewCurrencyId() + "&new_cur_name=" + orderCreationDto.getCurrencyName();
                return "redirect:/profile/create-order?" + parameters;
            } catch (CurrencyConverterException e) {
                logger.warn("Attempt to request the invalid currency");

                return "redirect:/profile/create-order?offer=" + offerId + "&convertError";
            }
        }
    }

    @PostMapping("/create-order")
    public String addOrder(@Valid @ModelAttribute("orderCreationDto") OrderCreationDto orderCreationDto,
                           BindingResult result, Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {

        if (result.hasErrors()) {
            model.addAttribute("orderCreationDto", orderCreationDto);
            model.addAttribute("changeCurrencyDto", new ChangeCurrencyDto());
            model.addAttribute("currencies", currenciesService.getAllCurrencies());
            return "createOrder";
        } else {
            try {
                ordersService.addOrder(orderCreationDto, userDetails.getAccount().getId());
                return "redirect:/profile/timetable/client?success";
            } catch (CreateOrderException e) {
                logger.warn("Unsuccessful attempt to create order: " + userDetails.getAccount().getEmail());

                model.addAttribute("orderCreationDto", orderCreationDto);
                model.addAttribute("changeCurrencyDto", new ChangeCurrencyDto());
                model.addAttribute("currencies", currenciesService.getAllCurrencies());
                model.addAttribute("error", e.getMessage());
                return "createOrder";
            }
        }
    }
}

