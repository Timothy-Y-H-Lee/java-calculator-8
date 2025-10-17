package calculator.domain;

import java.util.List;

public class CalculatorModel {
	private final List<String> userInputList;

	public CalculatorModel(List<String> userInputList) {
		this.userInputList = userInputList;
	}

	public List<String> getUserInputList() {
		return userInputList;
	}
}
