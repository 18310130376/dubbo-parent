package com.integration.boot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SingleFileUpload {

	@PostMapping("/upload") 
	public Map<String,Object> singleFileUpload(@RequestParam("file") MultipartFile file){
		Map<String,Object> resultMap = new HashMap<>();
	    if (file.isEmpty()) {
	    	resultMap.put("status","fail");
	        resultMap.put("msg","Please select a file to upload");
	        return resultMap;
	    }
	    try {
	        byte[] bytes = file.getBytes();
	        Path path = Paths.get("C:\\Users\\789\\Desktop\\uploadfile\\" + file.getOriginalFilename());
	        Files.write(path, bytes);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    resultMap.put("status","success");
	    resultMap.put("msg","operation successfully");
	    return resultMap;
	}
}
