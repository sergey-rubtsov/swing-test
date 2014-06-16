package com.my.app.repository;

import java.util.List;

import com.my.app.domain.Answer;
import com.my.app.domain.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Serg
 * Date: 15.03.13
 * Time: 13:35
 */

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
	List<Answer> findByAnswerLike(String question);
}
