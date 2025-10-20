package calculator.service;

public class CalculatorService {
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

	private static class InnerCalculatorService {

		private static final CalculatorService INSTANCE = new CalculatorService();
	}
	public static CalculatorService getInstance(){
		return InnerCalculatorService.INSTANCE;
	}
}
