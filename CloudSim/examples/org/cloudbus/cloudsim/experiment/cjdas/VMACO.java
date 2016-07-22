package org.cloudbus.cloudsim.experiment.cjdas;

public class VMACO {

	private int id;
	private double mips;
	private long size;
	private int numberOfPes;
	private double ram;
	private double bw;
	private String vmm;
	private HostACO host;

	public VMACO(int id, double mips, long size, int numberOfPes, double ram,
			double bw, String vmm) {

		setId(id);
		setMips(mips);
		setSize(size);
		setNumberOfPes(numberOfPes);
		setRam(ram);
		setBw(bw);
		setVmm(vmm);
	}

	public VMACO(int id, double mips, long size, int numberOfPes, double ram,
			double bw, String vmm, HostACO host) {

		setId(id);
		setMips(mips);
		setSize(size);
		setNumberOfPes(numberOfPes);
		setRam(ram);
		setBw(bw);
		setVmm(vmm);
		setHost(host);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMips() {
		return mips;
	}

	public void setMips(double mips) {
		this.mips = mips;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getNumberOfPes() {
		return numberOfPes;
	}

	public void setNumberOfPes(int numberOfPes) {
		this.numberOfPes = numberOfPes;
	}

	public double getRam() {
		return ram;
	}

	public void setRam(double ram) {
		this.ram = ram;
	}

	public double getBw() {
		return bw;
	}

	public void setBw(double bw) {
		this.bw = bw;
	}

	public String getVmm() {
		return vmm;
	}

	public void setVmm(String vmm) {
		this.vmm = vmm;
	}

	public HostACO getHost() {
		return host;
	}

	public void setHost(HostACO host) {
		this.host = host;
	}

}
