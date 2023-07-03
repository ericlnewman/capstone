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

import com.success.dao.SuccessForAllDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.service.SuccessForAllService;
import com.success.service.TestServiceStub;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestServiceStub.class)
/**
 * This test follows the given, when, then format for unit testing
 */
public class SuccessForAllServiceTest {

	@Autowired
	SuccessForAllService successForAllService;
	List<ConcernsDTO> concerns;
	private UserDTO user;
	
	@MockBean
	private SuccessForAllDAO successForAllDAO;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this); 
		
		user = new UserDTO();
		user.setId(123456789L);
		user.setConcerns("adhd");
		Mockito.when(successForAllDAO.save(user)).thenReturn(true);
		successForAllService.setSuccessForAllDAO(successForAllDAO);
	}
	
	@Test
	public void saveConcern_whenConcernIsEntered() {
		givenUserIsLoggedIntoSuccessForAll();
		whenUserSearchesForAConcern();
		whenUserAddsTextDetails();
		thenConcernIsSaved();
		
	}

	private void whenUserSearchesForAConcern() {
		try {
			concerns = successForAllService.fetchConcerns("adhd");
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
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
			boolean result = successForAllService.save(user);
			// Verify that the save() method is called on the successForAllDAO mock with the user object
	        Mockito.verify(successForAllDAO).save(user);
			// if it makes it here, the test passes
			assertTrue(result);
		} catch (Exception e) {
			// we should not get here, so fail
			fail();
		}
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
		try {
			concerns = successForAllService.fetchConcerns("adhd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void thenSuccessForAllReturnBehavior() {
		// TODO Auto-generated method stub
		boolean behaviorFound = false;
		for (ConcernsDTO concernsDTO : concerns) {
			if(concernsDTO.getConcernsName().contains("adhd")) 
			behaviorFound = true;
		
		}
		
	}

	

	

	private void givenUserIsLoggedIntoSuccessForAll() {
		
	}

	private void whenTheUserSearchesForAJunkConcernADHD() {
		try {
			concerns = successForAllService.fetchConcerns("dafdsagdsfl;,d");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void whenTheUserSearchesForAJunkConcernBehavior() {
		try {
			concerns = successForAllService.fetchConcerns("dafdsagdsfl;,d");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void thenSuccessForAllReturnsZeroResults() {
		assertEquals("Number of concerns returned", 0, concerns.size());
		
	}
}
