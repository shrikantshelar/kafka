package com.learning.kafka.controller;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.kafka.consumer.TestTopicConsumer;

@RestController
public class KafkaController {

	private KafkaTemplate<String, String> template;
	private TestTopicConsumer testTopicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, TestTopicConsumer testTopicConsumer) {
        this.template = template;
        this.testTopicConsumer = testTopicConsumer;
    }

    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String message) {
        template.send("TestTopic", message);
    }
    
    @GetMapping("/kafka/messages")
    public List<String> getMessages() {
        return testTopicConsumer.getMessages();
    }
}
