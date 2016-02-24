package voterAccess;

import static org.junit.Assert.*;


import org.junit.Test;

public class Encrypter {

	@Test
	public void testEncryptDecrypt() throws Exception {
		String texto="Hola mundo hacker";
		String textoEncriptado = es.uniovi.asw.types.Encrypter.encrypt(texto);
		String solucion =es.uniovi.asw.types.Encrypter.decrypt(textoEncriptado);
		assertTrue(solucion.equals(texto));
	}

}
