package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import calculator.domain.CalculatorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorModelTest {
	CalculatorModel calculatorModel;
	String inputString;

	@BeforeEach
	void setUp() {
		inputString = "1,2:3";
	}

	@Test
	void saveUserInput() {
		// given
		List<String> userInputList = new ArrayList<>(Arrays.asList(inputString.split("[,:]")));
		// when
		CalculatorModel calculatorModel = new CalculatorModel(userInputList);
		// then
		assertThat(userInputList).isEqualTo(calculatorModel.getUserInputList());
	}
}
