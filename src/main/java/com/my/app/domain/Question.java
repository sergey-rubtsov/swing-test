package com.my.app.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 11:29
 */

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String id;

    @NotNull
    @Size(min = 0, max = 8191)
    private String question = "";    
    
    //@OneToMany(cascade=CascadeType.ALL, mappedBy="question")
    
    @OneToMany(fetch=FetchType.LAZY, orphanRemoval=true, mappedBy="question")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private Set<Answer> answers = new HashSet<Answer>();
    
    public Set<Answer> getAnswers() {
        return answers;
    }    

    public Question() {
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

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
