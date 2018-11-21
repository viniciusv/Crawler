package com.crawler.unit.test.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.crawler.CrawlerApplicationTests;
import com.crawler.api.model.UserApp;
import com.crawler.api.repository.UserRepository;


public class UserServiceUnitTest  extends CrawlerApplicationTests {
	
	@MockBean
	private UserRepository userRepository;
	
	private static Validator validator;
	
	@Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
	
	@Test
	public void saveUserTestValidSuccess() {
		UserApp userMock = new UserApp(null, "vinicius", "viniciusv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorNameLengthMin() {
		UserApp userMock = new UserApp(null, "vini", "viniciusv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorNameLengthMax() {
		UserApp userMock = new UserApp(null, "viniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusviviniciusv", "viniciusv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorNameRequired() {
		UserApp userMock = new UserApp(null, "", "viniciusv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorUserNameRequired() {
		UserApp userMock = new UserApp(null, "vinicius", "", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorUserNameLengthMin() {
		UserApp userMock = new UserApp(null, "vinicius", "vinv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorUserNameLengthMax() {
		UserApp userMock = new UserApp(null, "vinicius", "viniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusvianaviniciusviviniciusv", "123asdX78C");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorPasswordRequired() {
		UserApp userMock = new UserApp(null, "vinicius", "viniciusv", "");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void saveUserTestValidErrorPasswordLengthMin() {
		UserApp userMock = new UserApp(null, "vinicius", "viniciusv", "123a");
		Set<ConstraintViolation<UserApp>> violations = this.validator.validate(userMock);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void findByUserNameTest() {
		UserApp userMock = new UserApp(null, "usuario", "viniciusv", "123AS324df");
		when(this.userRepository.findByUserName("viniciusv")).thenReturn(userMock);
		UserApp findUser = this.userRepository.findByUserName("viniciusv");
		
		assertEquals(findUser, userMock);
	}
}
