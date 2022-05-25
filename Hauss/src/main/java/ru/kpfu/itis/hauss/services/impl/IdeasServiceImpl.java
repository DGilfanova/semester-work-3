package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.dto.IdeaCreationDto;
import ru.kpfu.itis.hauss.dto.IdeaDto;
import ru.kpfu.itis.hauss.dto.IdeasPage;
import ru.kpfu.itis.hauss.exceptions.*;
import ru.kpfu.itis.hauss.helpers.constants.Constants;
import ru.kpfu.itis.hauss.models.*;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.repositories.IdeaCategoriesRepository;
import ru.kpfu.itis.hauss.repositories.IdeasRepository;
import ru.kpfu.itis.hauss.repositories.StyleRepository;
import ru.kpfu.itis.hauss.services.FilesService;
import ru.kpfu.itis.hauss.services.IdeasService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.DEFAULT_PAGE;

@RequiredArgsConstructor
@Service
public class IdeasServiceImpl implements IdeasService {

    private final IdeasRepository ideasRepository;
    private final AccountsRepository accountsRepository;
    private final IdeaCategoriesRepository categoryRepository;
    private final StyleRepository styleRepository;

    private final FilesService filesService;

    @Override
    public IdeaDto getIdeaById(Long ideaId) {
        return IdeaDto.from(ideasRepository.findById(ideaId)
                .orElseThrow(() -> new IdeaNotFoundException("Idea not found")));
    }

    @Override
    public IdeasPage getPublicIdeas(Integer page) {
        if (page == null) page = DEFAULT_PAGE;
        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_IDEAS_PAGE_SIZE, Sort.by("createdAt"));
        Page<Idea> postPage = ideasRepository.findAllByStatus(Status.PUBLIC, pageRequest);
        return IdeasPage.builder()
                .ideas(IdeaDto.from(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .currentPage(page)
                .build();
    }

    @Transactional
    @Override
    public IdeaDto addUserIdea(IdeaCreationDto newData, Long authUserId, MultipartFile multipartFile) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        IdeaCategory category = categoryRepository.findById(newData.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found. Sorry, choose another one"));

        Style style = styleRepository.findById(newData.getStyle())
                .orElseThrow(() -> new StyleNotFoundException("Style not found. Sorry, choose another one"));

        Status status = Status.PUBLIC;
        if (newData.getIsPrivate()) {
            status = Status.PRIVATE;
        }

        String photoName = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            photoName = filesService.upload(multipartFile);
        }

        return IdeaDto.from(ideasRepository.save(
                Idea.builder()
                        .account(account)
                        .title(newData.getTitle())
                        .content(newData.getContent())
                        .category(category)
                        .style(style)
                        .photoName(photoName)
                        .createdAt(LocalDateTime.now())
                        .status(status)
                        .build()));
    }

    @Transactional
    @Override
    public void deleteUserIdea(Long ideaId, Long authUserId) {
        Idea idea = ideasRepository.findById(ideaId)
                .orElseThrow(() -> new IdeaNotFoundException("Idea not found"));

        checkAccess(idea, authUserId);

        idea.setStatus(Status.DELETED);

        ideasRepository.save(idea);
    }

    @Transactional
    @Override
    public void addIdeaToFavorite(Long authUserId, Long ideaId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Idea idea = ideasRepository.findById(ideaId)
                .orElseThrow(() -> new IdeaNotFoundException("Idea not found. Maybe it was already deleted."));

        account.getFavoritesIdea().add(idea);

        accountsRepository.save(account);
    }

    @Override
    public List<IdeaDto> getFavouritesIdeas(Long authUserId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<IdeaDto> ideas = IdeaDto.from(account.getFavoritesIdea());
        ideas = ideas.stream().filter((el) -> !el.getStatus().equals(Status.DELETED)).collect(Collectors.toList());
        return ideas;
    }

    @Override
    public List<IdeaDto> getLastUserPublishedIdeas(Long authUserId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return IdeaDto.from(ideasRepository.findLastPublishedByUser(account.getId()));
    }

    private void checkAccess(Idea idea, Long authUserId) {
        if (!authUserId.equals(idea.getAccount().getId())) {
            throw new AccessException("You are not an author of this offer");
        }
    }
}

