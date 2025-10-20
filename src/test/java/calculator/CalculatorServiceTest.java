package calculator;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.domain.CalculatorModel;
import calculator.service.CalculatorService;

public class CalculatorServiceTest {
	CalculatorService calculatorService;

	@BeforeEach
	void setUp() {
		calculatorService = CalculatorService.getInstance();
	}

	@DisplayName("문자열에 기본 구분자들(쉼표(,)와 콜론(:))을 구분자가 포함되어 있는지 체크")
	@Test
	void isContainsDefaultDelimiters() {
		// give
		String userInput = "1,2:3";
		// when
		Boolean isContainsDefaultDelimiters = calculatorService.containsDefaultDelimiters((userInput));
		// then
		assertThat(isContainsDefaultDelimiters).isEqualTo(true);
	}
}
