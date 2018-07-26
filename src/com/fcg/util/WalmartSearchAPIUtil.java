package com.fcg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * This is API utility to fetch data from the Backend to verify on UI. 
 * 
 * @since July 2018
 * @author pankhurisharma
 *
 */
public class WalmartSearchAPIUtil {

	private final String API_KEY = "j2wfkr6tr64yyh3v9fmmajf3";

	/**
	 * This method is API call to get the search results using the Search String from the backend. 
	 * io-restassured is used for this.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public ArrayList<HashMap> getSearchResults(String searchText, HashMap<String, String> parameters)
	{
		RestAssured.baseURI = "https://api.walmartlabs.com/v1/";

		Response response = RestAssured.given().params(parameters).get("search");
		
		ArrayList<HashMap> searchResults = (ArrayList) response.jsonPath().getList("items");

		return searchResults;
	}

	/**
	 * This method is to get limited number of Top search results using the Search String from the backend. 
	 * io-restassured is used for this.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public ArrayList<String> getSearchResultNames(String searchText, int noOfResults) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("query", searchText);
		parameters.put("format", "json");
		parameters.put("apiKey", API_KEY);
		
		ArrayList<String> searchResultNames = new ArrayList<String>();
		ArrayList<HashMap> searchResults = getSearchResults(searchText, (HashMap<String, String>) parameters);

		int noOfResultsToSent;
		if(noOfResults == 0)
			noOfResultsToSent = searchResults.size();
		else 
			noOfResultsToSent = noOfResults;

		for (int i = 0; i < noOfResultsToSent; i++) {
			searchResultNames.add((String) searchResults.get(i).get("name"));
		}
		return searchResultNames;
	}

	/**
	 * This method is API call to get the Sorted search results using the Search String and Price sorting in the order speficied. 
	 * io-restassured is used for this.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public ArrayList<String> getSearchResultSortedByPrice(String searchText, String sortOrder, int noOfResults) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("query", searchText);
		parameters.put("format", "json");
		parameters.put("apiKey", API_KEY);
		parameters.put("sort", "price");
		parameters.put("order", sortOrder);
		
		ArrayList<String> searchResultNames = new ArrayList<String>();
		ArrayList<HashMap> searchResults = getSearchResults(searchText, (HashMap<String, String>) parameters);

		int noOfResultsToSent;
		if(noOfResults == 0)
			noOfResultsToSent = searchResults.size();
		else 
			noOfResultsToSent = noOfResults;

		for (int i = 0; i < noOfResultsToSent; i++) {
			searchResultNames.add((String) searchResults.get(i).get("name"));
		}
		return searchResultNames;
	}
}
