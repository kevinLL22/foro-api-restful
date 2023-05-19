package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.DataEditTopic;
import co.kevinl.forumapirestful.dto.DataNewTopic;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.model.TopicEntity;
import co.kevinl.forumapirestful.model.UserEntity;
import co.kevinl.forumapirestful.repository.CourseRepository;
import co.kevinl.forumapirestful.repository.TopicRepository;
import co.kevinl.forumapirestful.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    final UserRepository userRepository;
    final TopicRepository topicRepository;
    final CourseRepository courseRepository;

    public TopicService(UserRepository userRepository, TopicRepository topicRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
    }

    public TopicEntity dtoToEntity(DataNewTopic dataNewTopic){

        Optional<UserEntity> userRepositoryById = userRepository.findById(dataNewTopic.id_user());
        UserEntity userEntity = userRepositoryById.get();

        Optional<CourseEntity> courseRepositoryById = courseRepository.findById(dataNewTopic.id_course());
        CourseEntity courseEntity = courseRepositoryById.get();

        TopicEntity entity = new TopicEntity();
        entity.setIdUser(userEntity);
        entity.setIdCourse(courseEntity);
        entity.setTitle(dataNewTopic.title());
        entity.setMessage(dataNewTopic.message());
        entity.setDate(new Timestamp(System.currentTimeMillis() ));
        entity.setStatus(false);
        entity.setAuthor(userEntity.getName());
        entity.setCourse(courseEntity.getName());
        topicRepository.save(entity);
        return entity;
    }

    public TopicEntity returnById(Long id){
        Optional<TopicEntity> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()){
            return optionalTopic.get();
        } else {
            throw new RuntimeException("Topic not found");
        }
    }

    public List<TopicEntity> returnAll(){
        return topicRepository.findAll();
    }

    public TopicEntity editTopic(DataEditTopic dataEditTopic){
        Optional<TopicEntity> optionalTopic = topicRepository.findById(dataEditTopic.id());

        if (optionalTopic.isPresent()){
            TopicEntity topicEntity = optionalTopic.get();

            //adding new data
            topicEntity.setTitle(dataEditTopic.title());
            topicEntity.setMessage(dataEditTopic.message());
            topicRepository.save(topicEntity);
            return topicEntity;
        } else {
            throw new RuntimeException("Topic not found");
        }
    }

    public void deleteTopicById(Long id){
        topicRepository.deleteById(id);
    }
}
