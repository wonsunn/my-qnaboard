package com.qnaboard.myqnaboard.web;

import com.qnaboard.myqnaboard.domain.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return null;
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Answer answer = new Answer(loginUser, questionRepository.findById(questionId).get(), contents);

        return answerRepository.save(answer);
    }

    @DeleteMapping("/{answerId}")
    public Result delete(@PathVariable Long questionId, @PathVariable Long answerId, HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인을 해주세요");
        }

        Answer answer = answerRepository.getOne(answerId);
        User loginUser = HttpSessionUtils.getUserFromSession(session);

        if (!answer.isSameWriter(loginUser)) {
            return Result.fail("본인이 작성한 답글만 삭제할 수 있습니다.");
        }

        answerRepository.delete(answer);
        return Result.ok();
    }
}
