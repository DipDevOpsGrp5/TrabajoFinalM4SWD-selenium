package com.devops.dxcselenium;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.wait = new WebDriverWait(driver, 30);
	}

	@FindBy(id = "ahorro-input")
	private WebElement txtAhorro;

	@FindBy(id = "sueldo-input")
	private WebElement txtSueldo;

	@FindBy(xpath = "//div[@id='calculador']/button")
	private WebElement btnCalcular;

	@FindBy(id = "dxc")
	private WebElement lblDxc;

	@FindBy(id = "impuesto")
	private WebElement lblImpuesto;

	@FindBy(id = "saldo-restante")
	private WebElement lblSaldoRestante;

	@FindBy(id = "valor-uf")
	private WebElement lblValorUf;

	@FindBy(id = "ahorro-consultado")
	private WebElement lblAhorro;

	@FindBy(id = "sueldo-consultado")
	private WebElement lblSueldo;

	@FindBy(xpath = "//input[@id='sueldo-input']/following-sibling::p")
	private WebElement lblErrorMessage;

	public Dxc calculateDxc(Integer sueldo, Integer ahorro) {
		driver.navigate().refresh();
		txtAhorro.clear();
		txtAhorro.sendKeys(ahorro.toString());
		txtSueldo.clear();
		txtSueldo.sendKeys(sueldo.toString());
		btnCalcular.click();
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(lblDxc, "-")));
		int dxc = Integer.parseInt(lblDxc.getText());
		int impuesto = Integer.parseInt(lblImpuesto.getText());
		int saldo = Integer.parseInt(lblSaldoRestante.getText());
		double uf = Double.parseDouble(lblValorUf.getText());
		return new Dxc(sueldo, ahorro, dxc, impuesto, saldo, uf);
	}

	public String calculateAndGetMessage(Long sueldo, Long ahorro) {
		driver.navigate().refresh();
		txtAhorro.sendKeys(Keys.BACK_SPACE);
		txtSueldo.sendKeys(Keys.BACK_SPACE);
		if (ahorro != null)
			txtAhorro.sendKeys(ahorro.toString());
		if (sueldo != null)
			txtSueldo.sendKeys(sueldo.toString());
		btnCalcular.click();
		wait.until(ExpectedConditions.textToBePresentInElement(lblErrorMessage, "Error"));
		return lblErrorMessage.getText();
	}
}
