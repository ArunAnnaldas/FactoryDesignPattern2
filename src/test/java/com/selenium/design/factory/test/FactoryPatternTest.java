package com.selenium.design.factory.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.selenium.design.factory.DriverManager;
import com.selenium.design.factory.DriverManagerFactory;
import com.selenium.design.factory.DriverType;

public class FactoryPatternTest {

	DriverManager driverManager;
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = driverManager.getDriver();
	}

	@AfterMethod()
	public void afterMethod() {
		driverManager.quitDriver();
	}

	@Test
	public void launchTestAutomationGuru() {
		driver.get("http://testautomationguru.com");
		Assert.assertEquals("Vinsguru", driver.getTitle());
	}

	@Test
	public void launchGoogle() {
		driver.get("https://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}

	@Test
	public void launchYahoo() {
		driver.get("https://www.yahoo.com");
		Assert.assertEquals("Yahoo India | News, Finance, Cricket, Lifestyle and Entertainment", driver.getTitle());
	}
}
