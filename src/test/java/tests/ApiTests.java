package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIResponse;

import api.ApiClient;
import baseTest.BaseTest;

public class ApiTests extends BaseTest{

	@Test
	public void getUsersTest() {
		
		ApiClient apiClient=new ApiClient(playwright);
		APIResponse apiResponse=apiClient.getUsers();
		
		System.out.println("Status code "+apiResponse.status());
		System.out.println("Response "+apiResponse.text());
		
		Assert.assertEquals(apiResponse.status(), 200);
		apiClient.close();
	}
	
	@Test
	public void CreateUserTest() {
		ApiClient apiClient=new ApiClient(playwright);
		APIResponse apiResponse=apiClient.postUser();
		System.out.println("Status Code "+apiResponse.status());
		System.out.println("Res "+apiResponse.text());
		
		Assert.assertEquals(apiResponse.status(), 201);
		apiClient.close();
		
	}
}
