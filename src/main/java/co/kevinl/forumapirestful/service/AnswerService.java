package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.answer.DataNewAnswer;
import co.kevinl.forumapirestful.model.AnswerEntity;
import co.kevinl.forumapirestful.model.TopicEntity;
import co.kevinl.forumapirestful.model.UserEntity;
import co.kevinl.forumapirestful.repository.AnswerRepository;
import co.kevinl.forumapirestful.repository.TopicRepository;
import co.kevinl.forumapirestful.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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

    public AnswerEntity saveNewAnswer(DataNewAnswer dataNewAnswer){
        UserEntity userEntity = userRepository.findById(dataNewAnswer.id_user()).orElseThrow(() -> new RuntimeException("Course not found"));
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
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<AnswerEntity> findAll(){
        return answerRepository.findAll();
    }

    public void deleteById(Long id){
        answerRepository.deleteById(id);
    }
}
