package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.answer.DataAnswerResponse;
import co.kevinl.forumapirestful.dto.answer.DataEditAnswer;
import co.kevinl.forumapirestful.dto.answer.DataNewAnswer;
import co.kevinl.forumapirestful.model.AnswerEntity;
import co.kevinl.forumapirestful.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/answer")
public class AnswerController {
    final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<DataAnswerResponse> newAnswer(@RequestBody @Valid DataNewAnswer dataNewAnswer,
                                    UriComponentsBuilder builder){
        AnswerEntity answerEntity = answerService.saveNewAnswer(dataNewAnswer);
        DataAnswerResponse dataAnswerResponse = new DataAnswerResponse(answerEntity);
        URI uri = builder.path("/answer/{id}").buildAndExpand(answerEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(dataAnswerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAnswerResponse> getAnswerById(@PathVariable Long id){
        AnswerEntity answerEntity = answerService.findById(id);
        DataAnswerResponse dataAnswerResponse = new DataAnswerResponse(answerEntity);

        return ResponseEntity.ok(dataAnswerResponse);
    }

    @GetMapping
    public Page<DataAnswerResponse> findAllAnswer(Pageable pageable){
        return answerService.findAll(pageable).map(DataAnswerResponse::new);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable Long id){
        answerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DataAnswerResponse> editAnswer(@RequestBody DataEditAnswer dataEditAnswer){
        AnswerEntity answerEntity = answerService.editAnswer(dataEditAnswer);
        DataAnswerResponse dataAnswerResponse = new DataAnswerResponse(answerEntity);

        return ResponseEntity.ok(dataAnswerResponse);
    }
}
