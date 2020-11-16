/**
 * 
 */
package com.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/** Raunak Kumar Agarwal **/
@Controller
@RequestMapping(value = "/file")
@Slf4j
public class FileDownloadingController {

	@Autowired
	private FileOperationService fileOperationService;
	
	@PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		File newfile = new File("H:\\JioDownload\\"+file.getOriginalFilename());
		try {
			newfile.createNewFile();
			FileOutputStream stream  = new FileOutputStream(newfile);
			stream.write(file.getBytes());
			stream.close();
		} catch (IOException e) {
			log.error("Error occured while uploading the file"+file.getOriginalFilename());
			throw new FileOperationException("Error occured while uploading the file"+file.getOriginalFilename());
		}
		return new ResponseEntity<String>("File : "+ file.getOriginalFilename() + " Uploaded Successfully", HttpStatus.OK);
	}
	
	@GetMapping(value = "/download")
	public ResponseEntity<String> downloadFile(){
		fileOperationService.downloadeWorkBook(getDummyData());
		return new ResponseEntity<String>(" Downloaded Successfully", HttpStatus.OK);
	}
	
	
	private List<User> getDummyData(){
		List<User> users = new LinkedList<>();
		users.add(new User("raunaka", "Raunak Agarwal", "rka8870@gmail.com", "7001146127"));
		users.add(new User("raj", "Raj Khanna", "raj@gmail.com", "9333508848"));
		users.add(new User("binod", "Binod Rai", "binod@gmail.com", "8101755845"));
		return users;
	}
}

