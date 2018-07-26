package com.fcg.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fcg.genericlib.CommonMethods;
import com.fcg.genericlib.SolventSelenium;
import com.fcg.pagelib.HomePage;
import com.fcg.util.WalmartSearchAPIUtil;

public class VerifySearch {
	
	private WalmartSearchAPIUtil walMartAPI = null;
	private HomePage homePage = null;
	
	/**
	 * This is the setup method to launch the browser and open Walmart Home page.
	 * Initilizing Page Objcets and API utility class
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	@BeforeTest
	public void launchBrowser() {
		SolventSelenium.launchBrowser();
		homePage = new HomePage();
		walMartAPI = new WalmartSearchAPIUtil();
	}
	
	/**
	 * This is the data provider to test for multiple search string.
	 * Data can be provided using XML or excel or other means.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	@DataProvider(name = "Search_Strings")
	public static String[] searchString() {
	        return new String[] { "Shell Scripting" };
	  }
	
	/**
	 * This is first test to verify the search results. 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	@Test(dataProvider = "Search_Strings")
	public void verifySearch(String searchString) {
		homePage.performSearch(searchString);
		Assert.assertTrue(CommonMethods.compareList(homePage.getSearchResults(3), walMartAPI.getSearchResultNames(searchString, 3)), "Search results are not as expected");;
	}
	
	/**
	 * This is the second test to verify the sorted results. 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	@Test(dataProvider = "Search_Strings")
	public void verifySortingResults(String searchString) {
		homePage.performSearch(searchString);
		homePage.sortBy("Price: low to high");
		Assert.assertTrue(CommonMethods.compareList(homePage.getSearchResults(3), walMartAPI.getSearchResultSortedByPrice(searchString, "asc", 3)), "Sorted results are not as expected");;
	}
	
	/**
	 * Closing Browser.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	@AfterTest
	public void close() {
		SolventSelenium.driver.quit();
	}

}
