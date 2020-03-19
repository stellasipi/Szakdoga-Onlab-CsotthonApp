package hu.bme.vik.tbs.onlab.CsotthonApp.web;

import hu.bme.vik.tbs.onlab.CsotthonApp.model.Log;
import hu.bme.vik.tbs.onlab.CsotthonApp.repository.LogRepository;
import hu.bme.vik.tbs.onlab.CsotthonApp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/logs")
public class LogController {
    @Autowired
    LogService logService;

    @Autowired
    LogRepository logRepository;

    @GetMapping(value="/", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Log>> getAll(){
        List<Log> logs=logRepository.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping
    public void createLog(){

    }
}
