import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CamelCaseConverterTest {
	
	private CamelCaseConverter camelCase;
	
	@BeforeEach
	public void setup() {
		camelCase = new CamelCaseConverter();
	}
	
	
	@Test
	void aplicarCamelCaseEmNomeUnico() throws Exception {
		assertEquals("Lionel", camelCase.converter("lionel"));
	}
	
	@Test
	void deveConverterNomeSimplesMisturandoMaiusculoEMinusculo() throws Exception {
		assertEquals("Lionel", camelCase.converter("lIOnel"));
	}

	

}
