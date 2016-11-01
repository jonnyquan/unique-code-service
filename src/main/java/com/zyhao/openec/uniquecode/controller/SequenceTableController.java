package com.zyhao.openec.uniquecode.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyhao.openec.uniquecode.entity.SequenceTable;
import com.zyhao.openec.uniquecode.service.SequenceTableService;

@RestController
@RequestMapping(path = "/v1")
public class SequenceTableController {
	
    @Autowired  
    SequenceTableService sequenceTableService;
	
    @RequestMapping(value = "/sequence/create" ,method = RequestMethod.POST,consumes="application/json")
    public ResponseEntity<SequenceTable> add(@RequestBody SequenceTable sequence) throws Exception {
    	return Optional.ofNullable(sequenceTableService.createSequence(sequence))
                .map(sequenceTable -> new ResponseEntity(sequenceTable,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find createSequence"));
    	
    }
    
    @RequestMapping(value = "/sequence/currentValue" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> getCurrentValue(@RequestParam String name) throws Exception {
    	return Optional.ofNullable(sequenceTableService.getCurrentValue(name))
                .map(sequenceTable -> new ResponseEntity(sequenceTable,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find sequence"));
    	
    }
    
    @RequestMapping(value = "/sequence/nextValue" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> getNextValue(@RequestParam String name) throws Exception {
    	return Optional.ofNullable(sequenceTableService.getNextValue(name))
                .map(sequenceTable -> new ResponseEntity(sequenceTable,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find sequence"));
    	
    }
    
    @RequestMapping(value = "/sequence/modifyCurrentValue" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> modifyCurrentValue(@RequestParam String name,@RequestParam Long value) throws Exception {
    	return Optional.ofNullable(sequenceTableService.modifyCurrentValue(name,value))
                .map(sequenceTable -> new ResponseEntity(sequenceTable,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find sequence"));
    	
    }
    
    @RequestMapping(value = "/sequence/modifyIncremen" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> modifyIncremen(@RequestParam String name,@RequestParam Long value) throws Exception {
    	return Optional.ofNullable(sequenceTableService.modifyIncrement(name,value))
                .map(sequenceTable -> new ResponseEntity(sequenceTable,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find sequence"));
    	
    }
    
}
