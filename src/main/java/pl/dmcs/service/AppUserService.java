package pl.dmcs.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmcs.domain.AppUser;

import java.util.List;

public interface AppUserService {

    void addAppUser(AppUser appUser);

    void editAppUser(AppUser appUser);

    List<AppUser> listAppUser();

    @Secured("ROLE_ADMIN")
    void removeAppUser (long id);

    AppUser getAppUser(long id);

    AppUser findByLogin(String login);

    boolean activateUser(String token);
}
