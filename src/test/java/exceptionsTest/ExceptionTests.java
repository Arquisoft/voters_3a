package exceptionsTest;

import static org.junit.Assert.*;

import org.junit.Test;



import es.uniovi.asw.exceptions.UserNotFoundException;
import es.uniovi.asw.types.UserPass;

public class ExceptionTests {

	@Test
	public void test() {
		try {
			new UserNotFoundException(new UserPass("correo@dom.com", "randomPass"));
		} catch (RuntimeException e) {
			assertTrue(true);
		}
		
	}

}
