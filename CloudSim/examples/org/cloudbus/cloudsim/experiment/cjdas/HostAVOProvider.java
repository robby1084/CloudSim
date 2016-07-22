package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HostAVOProvider {

	private static final Double mips_1 = 1000.0;
	private static final Double mips_2 = 2000.0;
	private static final Double mips_3 = 3000.0;
	private static final Double[] mips = { mips_1, mips_2, mips_3 };
	private static final long size = 10000;
	private static final int numberOfPes = 100;
	private static final Double ram_1 = 10.0;
	private static final Double ram_2 = 12.0;
	private static final Double ram_3 = 15.0;
	private static final Double[] ram = { ram_1, ram_2, ram_3 };
	private static final Double bw_1 = 1.0;
	private static final Double bw_2 = 1.2;
	private static final Double bw_3 = 1.5;
	private static final Double[] bw = { bw_1, bw_1, bw_1 };

	public static List<HostACO> creatHost(int num) {

		List<HostACO> list = new ArrayList<HostACO>();
		for (int i = 1; i <= num; i++) {
			HostACO host = new HostACO(i, mips[new Random().nextInt(3)], size,
					numberOfPes, ram_1, bw_1, "host" + i, false);
			list.add(host);
		}
		return list;
	}
}
