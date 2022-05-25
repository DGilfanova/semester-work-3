package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kpfu.itis.hauss.models.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.IDEA_DATE_TEMPLATE;
import static ru.kpfu.itis.hauss.helpers.constants.Constants.PHOTO_DIRECTORY;
import static ru.kpfu.itis.hauss.helpers.processors.UploadFilesLinkProcessor.getLinkByPhotoName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeaDto {

    private Long id;
    private AccountDto account;
    private String category;
    private String style;
    private String title;
    private String content;
    private String createdAt;
    private String photoLink;
    private Status status;

    public static IdeaDto from(Idea idea) {
        return IdeaDto.builder()
                .id(idea.getId())
                .account(AccountDto.from(idea.getAccount()))
                .category(idea.getCategory().getName())
                .style(idea.getStyle().getName())
                .title(idea.getTitle())
                .content(idea.getContent())
                .createdAt(idea.getCreatedAt().format(DateTimeFormatter.ofPattern(IDEA_DATE_TEMPLATE)))
                .photoLink(getLinkByPhotoName(idea.getPhotoName()))
                .status(idea.getStatus())
                .build();
    }

    public static List<IdeaDto> from(List<Idea> ideas) {
        return ideas.stream().map(IdeaDto::from).collect(Collectors.toList());
    }

    public static List<IdeaDto> from(Set<Idea> ideas) {
        return ideas.stream().map(IdeaDto::from).collect(Collectors.toList());
    }
}
