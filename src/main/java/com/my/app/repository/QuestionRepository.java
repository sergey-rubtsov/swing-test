package com.my.app.repository;

import com.my.app.domain.Question;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Serg
 * Date: 13.03.13
 * Time: 14:14
 * maybe extends JpaSpecificationExecutor<Question>, JpaRepository<Question, Long>
 */

@Repository
public interface QuestionRepository extends CrudRepository<Question, String> {
    List<Question> findByQuestionLike(String question, Pageable pageable);
    List<Question> findByQuestionLike(String question);
}
