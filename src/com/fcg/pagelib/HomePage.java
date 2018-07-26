package com.fcg.pagelib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.fcg.genericlib.SolventSelenium;

/**
 * This is the Page Object class for Home Page of Walmart
 * 
 * @author pankhurisharma
 *
 */
public class HomePage extends SolventSelenium {
	
	@FindBy(id = "global-search-input")
	public WebElement searchTextBox;
	
	@FindBy(xpath = "//button[@title='Perform Search']")
	public WebElement performSearchButton;
	
	@FindBy(xpath = "//select[@class ='select-field-secondary']")
	public WebElement sortingList;
	
	@FindBy(xpath = "//div[@class='search-result-product-title listview']//a//span")
	public List<WebElement> searchResults;
	
	@FindBy(id = "global-search-clear")
	public WebElement clearSearch;

	/**
	 * Initializing Page Factory elements in the constructor 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is used to enter the text in search text box
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public void enterSearchText(String searchText) {
		searchTextBox.sendKeys(searchText);
	}
	
	/**
	 * This method is used to click on Search Button
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public void clickOnSearchButton() {
		performSearchButton.click();
	}
	
	/**
	 * This method is used to perform search 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public void performSearch(String searchText) {
		if (clearSearch.isDisplayed()) {
			clearSearch();	
		}
		waitForPageToLoad();
		enterSearchText(searchText);
		clickOnSearchButton();
		waitForPageToLoad();
	}
	
	/**
	 * This method is used to get all the search results.
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public ArrayList<String> getAllSearchResults() {
		ArrayList<String> resultNames = new ArrayList<String>();
		for (WebElement result : searchResults) {
			resultNames.add(result.getText());
		}
		return resultNames;
	}
	
	/**
	 * This method is used to get only limited number of Top results 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public ArrayList<String> getSearchResults(int noOfResults) {
		ArrayList<String> resultNames = new ArrayList<String>();
		for (int i = 0; i < noOfResults; i++) {
			resultNames.add(searchResults.get(i).getText());
		}
		return resultNames;
	}
	
	/**
	 * This method is used to perform sorting using Sorting Options 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public void sortBy(String sortBy) {
		Select sortingOptions = new Select(sortingList);
		sortingOptions.selectByVisibleText(sortBy);
		waitForElementToPresent("SearchInfoMessage");
	}
	
	/**
	 * This method is used to clear search 
	 *
	 * @since July 2018
	 * @author pankhurisharma
	 */
	public void clearSearch() {
		clearSearch.click();
	}

}
