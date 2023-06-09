package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.answer.DataEditAnswer;
import co.kevinl.forumapirestful.dto.answer.DataNewAnswer;
import co.kevinl.forumapirestful.model.AnswerEntity;
import co.kevinl.forumapirestful.model.TopicEntity;
import co.kevinl.forumapirestful.model.UserEntity;
import co.kevinl.forumapirestful.repository.AnswerRepository;
import co.kevinl.forumapirestful.repository.TopicRepository;
import co.kevinl.forumapirestful.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class AnswerService {

    final AnswerRepository answerRepository;
    final UserRepository userRepository;
    final TopicRepository topicRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public AnswerEntity saveNewAnswer(DataNewAnswer dataNewAnswer, HttpServletRequest request){
        UserEntity userEntity = userRepository.findById(Long.valueOf( request.getAttribute("idUser").toString() ))
                .orElseThrow(() -> new RuntimeException("Course not found"));
        TopicEntity topicEntity = topicRepository.findById(dataNewAnswer.id_topic()).orElseThrow(() -> new RuntimeException("Course not found"));
        AnswerEntity answerEntity = new AnswerEntity();

        answerEntity.setIdUser(userEntity);
        answerEntity.setIdTopic(topicEntity);
        answerEntity.setMessage(dataNewAnswer.message());
        answerEntity.setDate(new Timestamp(System.currentTimeMillis()) );
        answerEntity.setAuthor(userEntity.getName());
        answerEntity.setVotes(0);
        answerEntity.setSolve(false);

        answerRepository.save(answerEntity);

        return answerEntity;
    }

    public AnswerEntity findById(Long id){
        return answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("answer not found"));
    }

    public Page<AnswerEntity> findAll(Pageable pageable){
        return answerRepository.findAll(pageable);
    }

    public void deleteById(Long id){
        answerRepository.deleteById(id);
    }

    public AnswerEntity editAnswer(DataEditAnswer editAnswer){
        AnswerEntity answerEntity = answerRepository.findById(editAnswer.id())
                .orElseThrow(() -> new RuntimeException("answer not found"));
        answerEntity.setMessage(editAnswer.message());
        answerRepository.save(answerEntity);
        return answerEntity;
    }
}
