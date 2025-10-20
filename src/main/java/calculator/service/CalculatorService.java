package calculator.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import calculator.domain.CalculatorModel;

public class CalculatorService {
	private CalculatorModel calculatorModel;
	private CalculatorService() {
	}

	/**
	 * 기본 구분자 쉼표(,) 또는 콜론(:)이 사용자 입력 문자열에 있는지 확인하는 메소드
	 * @param userInput
	 * @return Boolean
	 */
	public static Boolean containsDefaultDelimiters(String userInput) {
		return userInput.contains(",") || userInput.contains(":");
	}

	/**
	 * "//"와 "\n" 사이의 커스텀 구분자를 추출하는 메소드
	 * @param userInput
	 * @return String
	 */
	public static String extractCustomDelimiter(String userInput) {
		String startDelimiter = "//";
		String endDelimiter = "\\n";

		Integer startIndex = userInput.indexOf(startDelimiter) + startDelimiter.length();
		Integer endIndex = userInput.indexOf(endDelimiter);
		if (endIndex == -1) {
			// 실제 줄바꿈 문자를 인식하도록 처리
			endDelimiter = "\n";
			endIndex = userInput.indexOf(endDelimiter);
		}

		return (startIndex != -1 && endIndex != -1 && startIndex < endIndex)
			? userInput.substring(startIndex, endIndex) : "";
	}

	/**
	 * 사용자로부터 입력받은 문자열 연산
	 * @param userInput
	 * @return Integer
	 */
	public Integer calcFromString(String userInput) {
		if (userInput.isEmpty()) {
			return 0;
		}
		return calculateSumOfNumbers(userInput);
	}

	// 문자열을 적절한 구분자로 분리한 후 숫자의 합을 계산하는 메소드
	public Integer calculateSumOfNumbers(String userInput) {
		// \\n을 실제 줄바꿈 문자로 변환
		userInput = userInput.replace("\\n", "\n");

		String content = userInput.substring(userInput.indexOf("\n") + 1); // 구분자 이후의 문자열

		// 기본 구분자 + 커스텀 구분자 모두 포함해서 처리
		String delimiter = "[,:" + extractCustomDelimiter(userInput) + "]";
		// Model 단에 사용자의 입력값을 저장
		calculatorModel = new CalculatorModel(splitByDelimiter(content, delimiter));

		// 숫자로 변환한 후 합계 계산
		return calculatorModel.getUserInputList().stream()
			.map(Integer::parseInt)  // 문자열을 Integer로 변환
			.reduce(0, Integer::sum);  // 합계
	}

	// 구분자를 기준으로 문자열을 분리하는 메소드
	public static List<String> splitByDelimiter(String userInput, String delimiter) {
		return Arrays.stream(userInput.split(delimiter))
			.map(String::trim)  // 공백 제거
			.peek(CalculatorService::validateNumber) // 숫자 유효성 검사
			.collect(Collectors.toList());  // 리스트로 변환
	}

	// 숫자 유효성 검사: 숫자가 아니거나 음수인 경우 예외 발생
	public static void validateNumber(String validateNumber) {
		try {
			Integer number = Integer.parseInt(validateNumber);
			if (number < 0) {
				throw new IllegalArgumentException("양수만 입력 가능합니다." + validateNumber);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자가 아닙니다. " + validateNumber);
		}
	}

	private static class InnerCalculatorService {

		private static final CalculatorService INSTANCE = new CalculatorService();
	}
	public static CalculatorService getInstance(){
		return InnerCalculatorService.INSTANCE;
	}
}
