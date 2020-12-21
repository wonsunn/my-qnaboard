package com.qnaboard.myqnaboard.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private String title;

    @Lob
    private String contents;

    private LocalDateTime createDate;

    @OneToMany(mappedBy="question")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Question() {}

    public Question (User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    public void update (String updatedTitle, String updatedContents) {
        this.title = updatedTitle;
        this.contents = updatedContents;
    }

    public boolean isSameWriter(User loginUser) {
        return this.writer.equals(loginUser);
    }
}
