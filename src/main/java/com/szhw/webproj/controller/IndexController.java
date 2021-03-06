package com.szhw.webproj.controller;

import com.szhw.webproj.common.CommonResult;
import com.szhw.webproj.common.GlobalConstant;
import com.szhw.webproj.persistent.entity.User;
import com.szhw.webproj.persistent.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author HJF
 * @date 2018/11/26 17:41
 */
@Controller
public class IndexController implements GlobalConstant {
    @Resource
    UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        if (null == session.getAttribute(SESSION_ATTR_KEY)) {
            return new ModelAndView("index");
        }
        return home(session);
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user", session.getAttribute(SESSION_ATTR_KEY));
        return modelAndView;
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(User user, HttpSession session) {
        CommonResult result = new CommonResult();
        User verifyUser = new User();
        verifyUser.setAccount(user.getAccount());
        Optional<User> userOptional = userRepository.findOne(Example.of(verifyUser));
        if (!userOptional.isPresent()) {
            result.setCode(CODE_USER_NOT_FOUND);
            result.setMessage("用户不存在");
            return result;
        }
        verifyUser = userOptional.get();
        if (!verifyUser.getPassword().equals(user.getPassword())) {
            result.setCode(CODE_USER_PASSWORD_WRONG);
            result.setMessage("密码错误");
            return result;
        }
        session.setAttribute(SESSION_ATTR_KEY, verifyUser);
        return result;
    }

    @GetMapping("quit")
    public String quit(HttpSession session) {
        session.removeAttribute(SESSION_ATTR_KEY);
        return "redirect:/";
    }
}
