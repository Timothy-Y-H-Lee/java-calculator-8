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

	private static class InnerCalculatorService {

		private static final CalculatorService INSTANCE = new CalculatorService();
	}
	public static CalculatorService getInstance(){
		return InnerCalculatorService.INSTANCE;
	}
}
