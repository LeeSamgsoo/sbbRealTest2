package com.asdf.qwer.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userCreateForm.getPassword().equals(userCreateForm.getPasswordCheck())) {
            bindingResult.rejectValue("password", "password incorrect", "비밀번호가 다릅니다.");
            return "signup_form";
        }
        try {
            this.userService.create(userCreateForm.getUsername(), userCreateForm.getPassword(), userCreateForm.getNickname());
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("signup error", "이미 가입된 유저입니다.");
            return "signup_form";
        }
        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
