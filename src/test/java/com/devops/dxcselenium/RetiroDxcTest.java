package com.devops.dxcselenium;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.Test;

public class RetiroDxcTest extends BaseConfig {

	private static final int UF_MIN = 35;
	private static final int UF_MAX = 150;

	@Test
	public void withdrawal_with_savings_less_than_35_uf() {
		HomePage homePage = new HomePage(driver);
		Integer sueldo = 1000000;
		Integer ahorro = 1000000;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), ahorro);
		assertEquals(calculatedDxc.getSaldo(), 0);
		assertEquals(calculatedDxc.getImpuesto(), 0);
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void withdrawal_with_savings_between_35_and_350_uf() {
		HomePage homePage = new HomePage(driver);
		int sueldo = 1000000;
		int ahorro = 6000000;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MIN * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), (ahorro - calculatedDxc.getDxc()));
		assertEquals(calculatedDxc.getImpuesto(), 0);
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void withdrawal_with_savings_between_350_and_1500_uf() {
		HomePage homePage = new HomePage(driver);
		int sueldo = 1324698;
		int ahorro = 31374219;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(ahorro * 0.1));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), 0);
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void withdrawal_with_savings_greater_than_1500_uf() {
		HomePage homePage = new HomePage(driver);
		int sueldo = 1064157;
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), 0);
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_less_than_1500000() {
		HomePage homePage = new HomePage(driver);
		int sueldo = 1499999;
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), 0);
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_1() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_1;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_2() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_2;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_3() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_3;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_4() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_4;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_5() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_5;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_6() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_6;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void taxes_with_salary_tramo_7() {
		HomePage homePage = new HomePage(driver);
		TramoImpuestoRenta tramo = TramoImpuestoRenta.TRAMO_7;
		int sueldo = generateRandomSalary(tramo);
		int ahorro = 49364127;
		Dxc calculatedDxc = homePage.calculateDxc(sueldo, ahorro);
		assertEquals(calculatedDxc.getDxc(), (int) Math.round(UF_MAX * calculatedDxc.getUf()));
		assertEquals(calculatedDxc.getSaldo(), ahorro - calculatedDxc.getDxc());
		assertEquals(calculatedDxc.getImpuesto(), (int) Math.round(calculatedDxc.getDxc() * tramo.factor));
		assertEquals(calculatedDxc.getSueldo(), sueldo);
		assertEquals(calculatedDxc.getAhorro(), ahorro);
	}

	@Test
	public void validate_message_with_salary_less_than_zero() {
		HomePage homePage = new HomePage(driver);
		long sueldo = -1;
		long ahorro = 1000000;
		String errorMessage = homePage.calculateAndGetMessage(sueldo, ahorro);
		assertEquals(errorMessage, "Error (400): sueldo deben ser igual o mayor a 0");
	}

	@Test
	public void validate_message_with_salary_greater_than_max_integer_value() {
		HomePage homePage = new HomePage(driver);
		long sueldo = Long.MAX_VALUE;
		long ahorro = 1000000;
		String errorMessage = homePage.calculateAndGetMessage(sueldo, ahorro);
		assertEquals(errorMessage,
				"Error (400): Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \""
						+ sueldo + "\"");
	}

	@Test
	public void validate_message_with_savings_less_than_zero() {
		HomePage homePage = new HomePage(driver);
		long sueldo = 1000000;
		long ahorro = -1;
		String errorMessage = homePage.calculateAndGetMessage(sueldo, ahorro);
		assertEquals(errorMessage, "Error (400): ahorro deben ser igual o mayor a 0");
	}

	@Test
	public void validate_message_with_savings_greater_than_max_integer_value() {
		HomePage homePage = new HomePage(driver);
		long sueldo = 1000000;
		long ahorro = Long.MAX_VALUE;
		String errorMessage = homePage.calculateAndGetMessage(sueldo, ahorro);
		assertEquals(errorMessage,
				"Error (400): Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \""
						+ ahorro + "\"");
	}

	@Test
	public void validate_message_without_ahorro_parameter() {
		HomePage homePage = new HomePage(driver);
		long sueldo = 1000000;
		String errorMessage = homePage.calculateAndGetMessage(sueldo, null);
		assertEquals(errorMessage, "Error (400): Required Integer parameter 'ahorro' is not present");
	}

	@Test
	public void validate_message_without_saldo_parameter() {
		HomePage homePage = new HomePage(driver);
		long ahorro = 1000000;
		String errorMessage = homePage.calculateAndGetMessage(null, ahorro);
		assertEquals(errorMessage, "Error (400): Required Integer parameter 'sueldo' is not present");
	}

	private int generateRandomSalary(TramoImpuestoRenta tramo) {
		switch (tramo) {
		case TRAMO_1:
			return new Random().nextInt(tramo.hasta - 1500000) + 1500000;
		default:
			return new Random().nextInt(tramo.hasta - tramo.desde) + tramo.desde;
		}
	}
}
