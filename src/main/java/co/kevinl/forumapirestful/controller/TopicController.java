package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.topic.DataEditTopic;
import co.kevinl.forumapirestful.dto.topic.DataNewTopic;
import co.kevinl.forumapirestful.dto.topic.DataTopicResponse;
import co.kevinl.forumapirestful.model.TopicEntity;
import co.kevinl.forumapirestful.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
@CrossOrigin("http://localhost:4200/**")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<DataTopicResponse> newTopic(@RequestBody @Valid DataNewTopic dataNewTopic,
                                                      UriComponentsBuilder builder, HttpServletRequest request){

        TopicEntity topicEntity = topicService.saveNewTopic(dataNewTopic, request);
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

    @GetMapping
    public Page<DataTopicResponse> returnAllTopics(Pageable pageable){
        return topicService.returnAll(pageable).map(DataTopicResponse::new);
    }

    @PutMapping
    public ResponseEntity<DataTopicResponse> returnTopicEdited(@RequestBody @Valid DataEditTopic dataEditTopic){
        TopicEntity topicEntity = topicService.editTopic(dataEditTopic);
        DataTopicResponse dataTopicResponse = new DataTopicResponse(topicEntity);
        return ResponseEntity.ok(dataTopicResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id){
        topicService.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }

}
