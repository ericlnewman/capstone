package com.successtest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.success.dao.UserDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.service.UserService;
import com.success.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceImpl.class)
/**
 * This test follows the given, when, then format for unit testing
 */
public class UserServiceTest {

	@Autowired
	UserService userService;
	List<ConcernsDTO> concerns;
	private UserDTO user;
	
	@MockBean
	private UserDAO userDAO;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this); 
		
		user = new UserDTO();
		user.setId(123456789L);
		user.setConcerns("adhd");
		Mockito.when(userDAO.save(user)).thenReturn(true);
		userService.setUserDAO(userDAO);
	}
	
	@Test
	public void fetchUserInfo_validateResultsForJunkDataADHD() {
		givenUserIsLoggedIntoSuccessForAll();
		whenTheUserSearchesForAJunkConcernADHD();
		thenSuccessForAllReturnsZeroResults();
	}
	
	@Test
	public void fetchUserInfo_validateResultsForJunkDataBehavoir() {
		givenUserIsLoggedIntoSuccessForAll();
		whenTheUserSearchesForAJunkConcernBehavior();
		thenSuccessForAllReturnsZeroResults();
	}
	
	@Test
	public void fetchUserInfo_validateResultsForADHD() {
		givenUserIsLoggedIntoSuccessForAll();
		whenTheUserSearchesForADHDConcernBehavior();
		thenSuccessForAllReturnBehavior();
	}
	
	private void whenTheUserSearchesForADHDConcernBehavior() {
		// TODO Auto-generated method stub
		concerns = userService.fetchConcerns("adhd");
		
		
	}

	private void thenSuccessForAllReturnBehavior() {
		// TODO Auto-generated method stub
		boolean behaviorFound = false;
		for (ConcernsDTO concernsDTO : concerns) {
			//if(concernsDTO.getCommon().contains("adhd"));
			behaviorFound = true;
		}
		
	}

	@Test
	public void saveConcern_whenConcernIsEntered() {
		givenUserIsLoggedIntoSuccessForAll();
		whenUserSearchesForAConcern();
		whenUserAddsTextDetails();
		thenConcernIsSaved();
		
	}

	private void whenUserSearchesForAConcern() {
		concerns = userService.fetchConcerns("adhd");
		
	}

	private void whenUserAddsTextDetails() {
		user = new UserDTO();
		ConcernsDTO concernDTO = concerns.get(0);
		user.setConcernId(concernDTO.getConcernsId());
		user.setId(123456789L);
		user.setConcerns("adhd");
		
	}

	private void thenConcernIsSaved() {
	
		try {
			boolean result = userService.save(user);
			// Verify that the save() method is called on the userDAO mock with the user object
	        Mockito.verify(userDAO).save(user);
			// if it makes it here, the test passes
			assertTrue(result);
		} catch (Exception e) {
			// we should not get here, so fail
			fail();
		}
	}

	private void givenUserIsLoggedIntoSuccessForAll() {
		
	}

	private void whenTheUserSearchesForAJunkConcernADHD() {
		concerns = userService.fetchConcerns("dafdsagdsfl;,d");
		
	}

	private void whenTheUserSearchesForAJunkConcernBehavior() {
		concerns = userService.fetchConcerns("dafdsagdsfl;,d");
		
	}
	private void thenSuccessForAllReturnsZeroResults() {
		assertEquals("Number of concerns returned", 0, concerns.size());
		
	}
}
