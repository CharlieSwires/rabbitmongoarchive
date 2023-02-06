package com.unicard.rabbit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(path = "/UniCard/api/v1")
public class RController  {

    Logger log = LoggerFactory.getLogger(RController.class);
    @Autowired
    private RabbitMQProducer producer;

    // http://localhost:9900/UniCard/publish
    @PostMapping("/dataarray")
    public ResponseEntity<Boolean> post( @RequestBody List<RequestBean> input){
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        producer.sendMessage(jsonStr);
        return ResponseEntity.ok(true);
    }    
  
}