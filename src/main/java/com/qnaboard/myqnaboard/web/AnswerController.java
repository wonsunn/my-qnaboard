package com.qnaboard.myqnaboard.web;

import com.qnaboard.myqnaboard.domain.Answer;
import com.qnaboard.myqnaboard.domain.AnswerRepository;
import com.qnaboard.myqnaboard.domain.QuestionRepository;
import com.qnaboard.myqnaboard.domain.User;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public String create(@PathVariable Long questionId, String contents, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Answer answer = new Answer(loginUser, questionRepository.findById(questionId).get(), contents);
        answerRepository.save(answer);

        return String.format("redirect:/questions/%d", questionId);
    }

}
