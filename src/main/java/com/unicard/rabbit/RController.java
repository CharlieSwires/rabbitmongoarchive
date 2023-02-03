package com.unicard.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/UniCard")
public class RController  {

    Logger log = LoggerFactory.getLogger(RController.class);
//    @Autowired
//    private ArchiveService service;
    @Autowired
    private RabbitMQProducer producer;

    // http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }    
//    @GetMapping(path="/getAllArray/{dateStart}/{dateEnd}/{page}", produces="application/json")
//    public ResponseEntity<ResponseBean[]> getAllArray(@PathVariable("dateStart") String dateStart, @PathVariable("dateEnd") String dateEnd,@PathVariable("page") Integer page) throws Exception {
//    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    	return new ResponseEntity<ResponseBean[]>(service.getAllArray(df.parse(dateStart), df.parse(dateEnd), page), HttpStatus.OK);
//    }
//    
//    @PostMapping(path="/add", produces="application/json", consumes="application/json")
//    public ResponseEntity<Boolean> post( @RequestBody List<RequestBean> input) {
//        
//        return new ResponseEntity<Boolean>(service.save(input), HttpStatus.OK);
//    }    
}