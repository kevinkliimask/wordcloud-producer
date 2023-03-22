package kevink.producer.service;

import kevink.producer.exception.InvalidFileException;
import kevink.producer.message.UploadMessage;
import kevink.producer.message.MQConfig;
import org.apache.tika.Tika;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class MessagePublishingService {

    @Autowired
    private RabbitTemplate template;

    public void publishFile(MultipartFile file) throws IOException {
        String fileData = handleFile(file);
        UploadMessage message = new UploadMessage(UUID.randomUUID().toString(), fileData, new Date());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);
    }

    private String handleFile(MultipartFile file) throws IOException {
        byte[] fileDataRaw = file.getBytes();

        if (fileDataRaw.length == 0)
            throw new InvalidFileException("File must not be empty");
        if (!(new Tika()).detect(fileDataRaw).contentEquals("text/plain"))
            throw new InvalidFileException("File must contain plain text");
        if (fileDataRaw.length > 3000000)
            throw new InvalidFileException("File must not exceed 3MB in size");

        return Base64.getEncoder().encodeToString(file.getBytes());
    }

}
