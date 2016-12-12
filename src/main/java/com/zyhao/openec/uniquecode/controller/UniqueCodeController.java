package com.zyhao.openec.uniquecode.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zyhao.openec.uniquecode.entity.SequenceTable;
import com.zyhao.openec.uniquecode.service.UniqueCodeService;



/**
 * 
 * @author zgy_c
 *
 */
@RestController
@RequestMapping(path = "/nologin")
public class UniqueCodeController {
	
    @Autowired  
    UniqueCodeService uniqueCodeService;
	
    @RequestMapping(value = "/uniqueCode" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> uniqueCode() throws Exception {
    	return Optional.ofNullable(uniqueCodeService.uniqueCode())
                .map(uniqueCode -> new ResponseEntity(uniqueCode,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find unique code service"));
    	
    }

    @RequestMapping(value = "/uniqueCodePatch" ,method = RequestMethod.PATCH)
    public ResponseEntity<SequenceTable> uniqueCodePatch() throws Exception {
    	System.out.println("-------------patch----------------");
    	return Optional.ofNullable(uniqueCodeService.uniqueCode())
                .map(uniqueCode -> new ResponseEntity(uniqueCode,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find unique code service"));
    	
    }
   
    @RequestMapping(value = "/uniqueCodePatch" ,method = RequestMethod.GET)
    public ResponseEntity<SequenceTable> uniqueCodeGET() throws Exception {
    	System.out.println("-------------GET----------------");
    	return Optional.ofNullable(uniqueCodeService.uniqueCode())
                .map(uniqueCode -> new ResponseEntity(uniqueCode,HttpStatus.OK))
                .orElseThrow(() -> new Exception("Could not find unique code service"));
    	
    }
    
}
