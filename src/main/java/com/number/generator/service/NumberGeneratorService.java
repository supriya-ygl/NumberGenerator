package com.number.generator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.util.CollectionUtils;

import com.number.generator.model.NumberGenerator;

public interface NumberGeneratorService {
	
	public static final String path = "tmp\\";
	public static final String filename_append = "_output";

	public List<Integer> generateNumbers(NumberGenerator number);
	
	@Async("asyncExecutor")
	public default void writeToFile(String filename, List<Integer> numbers) {

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(path + filename + filename_append));
			for (Integer num : numbers) {
				pw.println(num);
				Thread.sleep(1000L);
			}
			pw.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public default List<String> readFromFile(String filename) throws FileNotFoundException {
		List<String> lines = Collections.emptyList();
		filename = path + filename + filename_append;
		if (isFileExists(filename)) {
			try {
				lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
			}

			catch (IOException e) {

				e.printStackTrace();
			}
		}
		else {
			throw new FileNotFoundException("Not Found");
		}
		return lines;
	}

	default Boolean isFileExists(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			return true;
		}

		return false;
	}
	
	default Boolean isFileLocked(String filename) throws FileNotFoundException
	{
		try {
			if(!CollectionUtils.isEmpty(readFromFile(filename))) {
				return true;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	    return false;
	}

	
	

}
