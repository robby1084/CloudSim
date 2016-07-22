package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VMACOProvider {

	private static final Double mips_1 = 250.0;
	private static final Double mips_2 = 500.0;
	private static final Double mips_3 = 750.0;
	private static final Double[] mips = { mips_1, mips_2, mips_3 };
	private static final long size = 1;
	private static final int numberOfPes = 1;
	private static final Double ram_1 = 2.5;
	private static final Double ram_2 = 2.0;
	private static final Double ram_3 = 3.0;
	private static final Double[] ram = { ram_1, ram_2, ram_3 };
	private static final Double bw_1 = 0.25;
	private static final Double bw_2 = 0.3;
	private static final Double bw_3 = 0.2;
	private static final Double[] bw = { bw_1, bw_1, bw_1 };

	public static List<VMACO> creatVM(int num) {

		List<VMACO> list = new ArrayList<VMACO>();
		for (int i = 1; i <= num; i++) {
			VMACO vm = new VMACO(i, mips[new Random().nextInt(3)], size,
					numberOfPes, ram_1, bw_1, "vm" + i);
			list.add(vm);
		}
		return list;
	}
}
