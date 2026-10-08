package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ApiClient {
	
	private Playwright playwright;
	private APIRequestContext request;
	
	public ApiClient(Playwright playwright) {
		this.playwright=playwright;
		request=playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL("https://reqres.in"));
		
	}
	
	public APIResponse getUsers() {
		return request.get("/api/users?page=2");
	}
	
	public APIResponse postUser() {
		  String requestBody = """
              {
                  "name": "Mahi",
                  "job": "QA Engineer"
              }
              """;
		  return request.post("/api/users",RequestOptions.create().setHeader("Content-Type", "application/json").setData(requestBody));

	}
	
	public void close() {
		request.dispose();
	}

}
