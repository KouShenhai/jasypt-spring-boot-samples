package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTest {

	@Autowired
	ConfigurableEnvironment environment;

	@Autowired
	MyService service;

	@Autowired
	ItemConfig itemConfig;

	@Autowired
    SimpleBean simpleBean;

	@Autowired
	@Qualifier("encryptorBean")
	StringEncryptor encryptor;

	@BeforeAll
	public static void before() {
		System.setProperty("jasypt.encryptor.password", "password");
	}

	@Test
	public void testEnvironmentProperties() {
		System.out.println(environment);
		assertEquals("chupacabras", environment.getProperty("secret.property"));
		assertEquals("chupacabras", environment.getProperty("secret2.property"));
		assertEquals("chupacabras", environment.getProperty("secret3.property"));
	}

	@Test
	public void testServiceProperties() {
		assertEquals("chupacabras", service.getSecret());
	}

    @Test
    public void testXMLProperties() {
        assertEquals("chupacabras", simpleBean.getValue());
    }

    @Test
    public void testConfigurationPropertiesProperties() {
        assertEquals("chupacabras", itemConfig.getPassword());
        assertEquals("my configuration", itemConfig.getConfigurationName());
        assertEquals(2, itemConfig.getItems().size());
        assertEquals("item1", itemConfig.getItems().get(0).getName());
        assertEquals(new Integer(1), itemConfig.getItems().get(0).getValue());
        assertEquals("item2", itemConfig.getItems().get(1).getName());
        assertEquals(new Integer(2), itemConfig.getItems().get(1).getValue());
    }

	@Test
	public void testEncryptDecrypt() {
		String message = "embedded-client";
		String encrypted = encryptor.encrypt(message);
		System.out.println("Encrypted Message: " + encrypted);
		assertEquals(message, encryptor.decrypt(encrypted));
	}
}
