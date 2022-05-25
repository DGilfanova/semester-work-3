package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.models.Idea;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.PHOTO_DIRECTORY;
import static ru.kpfu.itis.hauss.helpers.processors.UploadFilesLinkProcessor.getLinkByPhotoName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String aboutMe;
    private String photoLink;
    private List<IdeaDto> ideas;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .aboutMe(account.getAboutMe())
                .photoLink(getLinkByPhotoName(account.getPhotoName()))
                .build();
    }

    public static AccountDto from(Account account, List<Idea> ideas) {
        AccountDto accountDto = AccountDto.from(account);
        accountDto.setIdeas(IdeaDto.from(ideas));
        return accountDto;
    }

    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
