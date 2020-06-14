package com.number.generator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.number.generator.model.NumberGenerator;

@Service
@Primary
public class NumberGeneratorServiceImpl implements NumberGeneratorService {

	
	@Override
	public List<Integer> generateNumbers(NumberGenerator number) {

		List<Integer> generatedNumbers = new ArrayList<Integer>();
		int goal = number.getGoal();
		int step = number.getStep();
		int count = goal % step;
		while (goal >= count) {
			generatedNumbers.add(goal);
			goal -= step;
		}
		return generatedNumbers;
	}

}
