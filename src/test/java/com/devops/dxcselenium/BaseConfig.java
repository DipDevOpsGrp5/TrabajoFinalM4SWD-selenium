package com.devops.dxcselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseConfig {

	private static final String BASE_URL = System.getProperty("base.url", "http://localhost:3000/");
	protected WebDriver driver;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(BASE_URL);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
