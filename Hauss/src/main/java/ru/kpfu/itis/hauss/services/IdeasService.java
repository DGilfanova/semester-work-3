package ru.kpfu.itis.hauss.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.dto.*;
import ru.kpfu.itis.hauss.models.Account;

import java.util.List;

public interface IdeasService {
    void addIdeaToFavorite(Long authUserId, Long ideaId);
    IdeasPage getPublicIdeas(Integer page);
    IdeaDto getIdeaById(Long ideaId);
    IdeaDto addUserIdea(IdeaCreationDto newData, Long authUserId, MultipartFile multipartFile);
    void deleteUserIdea(Long ideaId, Long authUserId);
    List<IdeaDto> getFavouritesIdeas(Long authUserId);
    List<IdeaDto> getLastUserPublishedIdeas(Long authUserId);
}

