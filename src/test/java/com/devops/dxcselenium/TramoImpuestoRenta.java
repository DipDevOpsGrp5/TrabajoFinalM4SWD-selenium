package com.devops.dxcselenium;

import java.util.Arrays;


public enum TramoImpuestoRenta {
	TRAMO_EXENTO(0, 752004, 0),
	TRAMO_1(752004, 1671120, 0.04),
	TRAMO_2(1671120, 2785200, 0.08),
	TRAMO_3(2785200, 3899280, 0.135),
	TRAMO_4(3899280, 5013360, 0.23),
	TRAMO_5(5013360, 6684480, 0.304),
	TRAMO_6(6684480, 17268240, 0.35),
	TRAMO_7(17268240, 2147483647, 0.4);

	int desde;
	int hasta;
	double factor;

	private TramoImpuestoRenta(int desde, int hasta, double factor) {
		this.desde = desde;
		this.hasta = hasta;
		this.factor = factor;
	}

	public static TramoImpuestoRenta findSectionBySalary(int sueldo) {
		return Arrays.asList(TramoImpuestoRenta.values()).stream().filter(t -> t.desde < sueldo && sueldo < t.hasta)
				.findFirst().orElse(TRAMO_7);
	}
}
