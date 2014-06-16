package com.my.app.domain;

import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
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
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class)
})
public class Question implements Serializable {

    private static final long serialVersionUID = 4932696682686624680L;

	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @DocumentId
    public String id;

    @NotNull
    @Size(min = 0, max = 8191)
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Analyzer(definition = "customanalyzer")
    private String question = "";    
    
    @OneToMany(orphanRemoval=true, mappedBy="question")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @IndexedEmbedded
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