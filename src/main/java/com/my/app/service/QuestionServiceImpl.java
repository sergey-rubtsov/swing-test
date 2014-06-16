package com.my.app.service;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;
import com.my.app.repository.AnswerRepository;
import com.my.app.repository.QuestionRepository;
import com.my.app.utils.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Serg Date: 13.03.13 Time: 14:33
 */

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public List<Question> findQuestionsBySequence(String question,
			int pageNumber, int countPerPage) {
		Sort sort = new Sort(Sort.Direction.ASC, "question");
		return questionRepository.findByQuestionLike('%' + question + '%',
				new Paging(pageNumber, countPerPage, 0, sort));
	}

	@Override
	public List<Question> findQuestionsBySequence(String question) {
		return questionRepository.findByQuestionLike('%' + question + '%');
	}

	@Override
	public List<Question> findAllQuestions() {
		return (List<Question>) questionRepository.findAll();
	}

	@Override
	public long countQuestions() {
		return questionRepository.count();
	}

	@Override
	public Question saveQuestion(Question question) {
		for (Answer answer : question.getAnswers()) {
			answer.setQuestion(question);
			answerRepository.save(answer);
		}
		return questionRepository.save(question);
	}

	@Override
	public void deleteQuestion(Question question) {
		questionRepository.delete(question);
	}

	@Override
	public void deleteQuestion(String id) {
		questionRepository.delete(id);
	}

	@Override
	public List<Question> searchQuestion(String keywords) {
		return findQuestionsBySequence(keywords);
	}

	@Override
	public Question findQuestion(String id) {
		return questionRepository.findOne(id);
	}

	@Override
	public List<Question> searchQuestionByAnswer(String keywords) {
		List<Answer> answers = answerRepository
				.findByAnswerLike('%' + keywords + '%');
		HashMap<String, Question> table = new HashMap<String, Question>();
		for (Answer a : answers) {
			table.put(a.getQuestion().getId(), a.getQuestion());
		}
		List<Question> questions = new ArrayList<Question>(table.values());
		return questions;

	}

	@Override
	public List fullTextSearch(String matching) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly=false)
	public List<Question> fullTextSearch(String matching) {		

		List<Question> result = null;

		EntityManager entityManager = getEntityManager();
		//EntityTransaction trans = entityManager.getTransaction();

		//System.err.println("Transaction isActive () == " + trans.isActive());

		if (entityManager != null) {
			try {
				FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
						.getFullTextEntityManager(entityManager);
				try {
					fullTextEntityManager.createIndexer(Question.class).startAndWait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				entityManager.getTransaction().begin();

				QueryBuilder qb = fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Question.class).get();
				org.apache.lucene.search.Query luceneQuery = qb.keyword()
						.onFields("question", "subtitle", "authors.name")
						.matching(matching).createQuery();

				javax.persistence.Query jpaQuery = fullTextEntityManager
						.createFullTextQuery(luceneQuery, Question.class);

				result = jpaQuery.getResultList();

				entityManager.getTransaction().commit();

			} finally {
				entityManager.close();
			}
		} else {
			System.err.println("EntityManager is null");
		}
		return result;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}*/
	
}
