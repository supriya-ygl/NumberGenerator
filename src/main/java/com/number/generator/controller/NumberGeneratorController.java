package com.number.generator.controller;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.number.generator.model.NumberGenerator;
import com.number.generator.model.Result;
import com.number.generator.service.NumberGeneratorService;

@RestController
@RequestMapping("/api")
public class NumberGeneratorController {

	@Autowired
	NumberGeneratorService numberGeneratorService;

	/**
	 * Write Generated numbers to file and return task-id.
	 * 
	 * @param generateNumber
	 * @return
	 */
	@PostMapping(path = "/generate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UUID> writeToFile(@RequestBody NumberGenerator generateNumber) {

		UUID taskId = UUID.randomUUID();
		numberGeneratorService.writeToFile(taskId.toString(), numberGeneratorService.generateNumbers(generateNumber));
		return new ResponseEntity<>(taskId, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/tasks/{taskid}/status")
	public Result printStatus(@PathVariable String taskid) {
		Result result = new Result();
		try {
				if (numberGeneratorService.isFileLocked(taskid)) {
					result.setResult("IN-PROGRESS");
				} else {
					result.setResult("COMPLETE");
				}
		}catch (FileNotFoundException e) {
			result.setResult("ERROR :: FILE NOT FOUND ");
		} 
		catch (Exception e) {
			result.setResult("ERROR ");
		}

		return result;

	}

	/**
	 * Read values from file in descending order
	 * 
	 * @param taskid
	 * @return
	 */
	@GetMapping(path = "/tasks/{taskid}")
	public Result writeFromFile(@PathVariable String taskid) {
		Result result = new Result();
		List<String> numbers = Collections.emptyList();
		try {
			numbers = numberGeneratorService.readFromFile(taskid);
		} catch (FileNotFoundException e) {
			result.setResult("File Not Found with taskid" + taskid);
			e.printStackTrace();
		}
		if (!CollectionUtils.isEmpty(numbers)) {
			String value = StringUtils.join(numbers, ",");
			result.setResult(value);
		}
		return result;
	}

}
