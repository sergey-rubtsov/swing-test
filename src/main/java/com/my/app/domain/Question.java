package com.my.app.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 11:29
 */

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @NotNull
    @Size(min = 0)
    private String question = "";

    @OneToMany(mappedBy="question", orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<Answer>();

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }
    }
}
