package others;
import org.apache.commons.lang3.RandomStringUtils;

public class JavaPrograms {
	static String randAlphabetic ="";

	public static void main(String[] args) {
		genRandAlphabetic(17);
	}
	
	public static void genRandAlphabetic(int alphaCount) {
		randAlphabetic = RandomStringUtils.randomAlphabetic(alphaCount);
		System.out.println("Rand Alphabetic "+ randAlphabetic);
	}
}
