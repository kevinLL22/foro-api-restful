package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.DataEditTopic;
import co.kevinl.forumapirestful.dto.DataNewTopic;
import co.kevinl.forumapirestful.dto.DataTopicResponse;
import co.kevinl.forumapirestful.model.TopicEntity;
import co.kevinl.forumapirestful.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<DataTopicResponse> newTopic(@RequestBody @Valid DataNewTopic dataNewTopic,
                                   UriComponentsBuilder builder){

        TopicEntity topicEntity = topicService.dtoToEntity(dataNewTopic);
        DataTopicResponse dataTopicResponse = new DataTopicResponse(topicEntity);

        URI uri = builder.path("/topic/{id}").buildAndExpand(topicEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(dataTopicResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopicResponse> returnTopicById(@PathVariable Long id){
        TopicEntity topicEntity = topicService.returnById(id);
        DataTopicResponse dataTopicResponse = new DataTopicResponse(topicEntity);
        return ResponseEntity.ok(dataTopicResponse);
    }

    //todo add pageable
    @GetMapping
    public List<DataTopicResponse> returnAllTopics(){
        return topicService.returnAll().stream().map(DataTopicResponse::new).toList();
    }

    @PutMapping
    public ResponseEntity<DataTopicResponse> returnTopicEdited(@RequestBody @Valid DataEditTopic dataEditTopic){
        TopicEntity topicEntity = topicService.editTopic(dataEditTopic);
        DataTopicResponse dataTopicResponse = new DataTopicResponse(topicEntity);
        return ResponseEntity.ok(dataTopicResponse);
    }

}
