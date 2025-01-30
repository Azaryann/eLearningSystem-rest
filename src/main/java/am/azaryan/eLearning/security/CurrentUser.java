package am.azaryan.eLearning.security;

import am.azaryan.eLearning.entity.user.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
}
