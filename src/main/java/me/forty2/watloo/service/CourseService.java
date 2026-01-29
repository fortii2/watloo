package me.forty2.watloo.service;

import me.forty2.watloo.entity.BotUser;
import me.forty2.watloo.entity.Term;
import me.forty2.watloo.enums.UserState;
import me.forty2.watloo.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private UserService userService;

    @Autowired
    private TermRepository termRepository;

    public SendMessage registerCourse(Long chatId, User user) {
        BotUser botUser = userService.getOne(user);
        botUser.setUserState(UserState.AWAITING_TERM_SELECTION);
        userService.save(botUser);

        return allTerms(chatId, user);
    }

    public SendMessage allTerms(Long chatId, User user) {
        List<Term> terms = termRepository.findTop4ByTermEndDateAfterOrderByTermBeginDateAsc(LocalDateTime.now());

        BotUser botUser = userService.getOne(user);
        userService.save(botUser);

        return SendMessage.builder()
                .chatId(chatId)
                .text("Please select one term")
                .replyMarkup(getReplyKeyboardMarkup(terms))
                .build();
    }

    private static ReplyKeyboardMarkup getReplyKeyboardMarkup(List<Term> terms) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(terms.get(0).getName());
        row1.add(terms.get(1).getName());
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(terms.get(2).getName());
        row2.add(terms.get(3).getName());
        keyboard.add(row2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboard);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        return replyKeyboardMarkup;
    }

    public SendMessage bindTerm(Long chatId, String messageText, User user) {
        BotUser botUser = userService.getOne(user);
        botUser.setBoundTerm(messageText);
        botUser.setUserState(UserState.AWAITING_COURSE_INPUT);
        userService.save(botUser);

        String response = "Please input course name like CS444.";

        return SendMessage
                .builder()
                .chatId(chatId)
                .text(response)
                .build();
    }
}
