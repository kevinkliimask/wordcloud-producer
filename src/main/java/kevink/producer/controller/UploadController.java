package kevink.producer.controller;

import kevink.producer.service.MessagePublishingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class UploadController {

    private final MessagePublishingService messagePublishingService;

    public UploadController(MessagePublishingService messagePublishingService) {
        this.messagePublishingService = messagePublishingService;
    }

    @GetMapping(value = "{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Integer> getWordCounts(@PathVariable("uuid") String uuid) {
        return messagePublishingService.publishGetRequest(uuid);
    }

    @PostMapping(value = "upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public String upload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return messagePublishingService.publishFile(file);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleIOException(IOException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
