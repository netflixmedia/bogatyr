package ch.orwell.bogatyr.test;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import junit.framework.TestCase;
import ch.orwell.bogatyr.crypto.AsymmCrypto;
import ch.orwell.bogatyr.crypto.SymmCrypto;
import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * Tests the AsymmCrypro and SymmCrypto
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070626
 */
public class CryptoTest extends TestCase {
	
	private AsymmCrypto asymmCrypto;
	private SymmCrypto symmCrypto;
	private String toEncrypt = new String();

    @Override
	protected void setUp() {
    	this.asymmCrypto = new AsymmCrypto();
    	this.symmCrypto = new SymmCrypto();
    }
    
    public void testAsymmCrypto(){
    	
    	// No key was generated yet
    	PublicKey publicKey = this.asymmCrypto.getPublicKey();
    	assertTrue(publicKey == null);
    	
    	// Generate key
    	try {
    		this.asymmCrypto.generateKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// No a key have to exist
		publicKey = this.asymmCrypto.getPublicKey();
		assertTrue(publicKey != null);
		
		
		// Generate random string to crypt (Dirty way...)
		this.toEncrypt += Math.random();
		
		// Encrypt it
		byte[] toEncryptInBytes = null;
		byte[] encrypted = null;
		try {
			toEncryptInBytes = GeneralHelper.getBytes(this.toEncrypt);
			encrypted = this.asymmCrypto.encrypt(toEncryptInBytes, publicKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Simple tests
		assertTrue(toEncryptInBytes != encrypted);
		
		// Test decrypting with public key
		byte[] decrypted = null;
		String expectedObject = new String();
		try {
			decrypted = this.asymmCrypto.decrypt(encrypted);
			expectedObject = (String)GeneralHelper.getObject(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Back testing
		assertTrue(this.toEncrypt.equals(expectedObject));
    }
    
    public void testSymmCrypto(){
    	
    	// No key was generated yet
    	SecretKey secretKey = this.symmCrypto.getKey();
    	assertTrue(secretKey == null);
    	
    	// Generate key
    	try {
    		this.symmCrypto.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// No a key have to exist
		secretKey = this.symmCrypto.getKey();
		assertTrue(secretKey != null);
		
		
		// Generate random string to crypt (Dirty way...)
		this.toEncrypt += Math.random();
		
		// Encrypt it
		byte[] toEncryptInBytes = null;
		byte[] encrypted = null;
		try {
			toEncryptInBytes = GeneralHelper.getBytes(this.toEncrypt);
			encrypted = this.symmCrypto.encrypt(toEncryptInBytes, secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Simple tests
		assertTrue(toEncryptInBytes != encrypted);
		
		// Test decrypting with public key
		byte[] decrypted = null;
		String expectedObject = new String();
		try {
			decrypted = this.symmCrypto.decrypt(encrypted, secretKey);
			expectedObject = (String)GeneralHelper.getObject(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Back testing
		assertTrue(this.toEncrypt.equals(expectedObject));
    }
    
}
