package com.epam.training.sportsbetting.mvc.login.transformer;

import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.mvc.login.model.LoginModel;
import org.springframework.stereotype.Component;


@Component
public class LoginModelTransformer {

    public User transformLoginModelToUser(LoginModel loginModel) {
        User result = new User();
        result.setEmail(loginModel.getEmail());
        result.setPassword(loginModel.getPassword());
        return result;
    }

}
