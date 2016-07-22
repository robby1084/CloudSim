package org.cloudbus.cloudsim.experiment.cjdas;

import java.util.ArrayList;
import java.util.List;

public class HostACO {

	private int id;
	private List<VMACO> vmlist = new ArrayList<VMACO>();
	private double mips;
	private long size;
	private int numberOfPes;
	private double ram;
	private double bw;
	private String hostname;
	private boolean isActive;
	
	private double currentMips;
	private long currentSize;
	private int currentNumberOfPes;
	private double currentRam;
	private double currentBw;

	public HostACO(int id, double mips, long size, int numberOfPes, double ram,
			double bw, String hostname, boolean isActive) {
		
		setId(id);
		setMips(mips);
		setSize(size);
		setNumberOfPes(numberOfPes);
		setRam(ram);
		setBw(bw);
		setHostname(hostname);
		setActive(isActive);
		
		setCurrentBw(bw);
		setCurrentMips(mips);
		setCurrentNumberOfPes(numberOfPes);
		setCurrentRam(ram);
		setCurrentSize(size);
	}
	
	public HostACO(int id, double mips, long size, int numberOfPes, double ram,
			double bw, String hostname, boolean isActive,List<VMACO> list) {
		
		setId(id);
		setMips(mips);
		setSize(size);
		setNumberOfPes(numberOfPes);
		setRam(ram);
		setBw(bw);
		setHostname(hostname);
		setActive(isActive);
		setVmlist(list);
		
		setCurrentBw(bw);
		setCurrentMips(mips);
		setCurrentNumberOfPes(numberOfPes);
		setCurrentRam(ram);
		setCurrentSize(size);
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

	public List<VMACO> getVmlist() {
		return vmlist;
	}

	public void setVmlist(List<VMACO> vmlist) {
		this.vmlist = vmlist;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getCurrentMips() {
		return currentMips;
	}

	public void setCurrentMips(double currentMips) {
		this.currentMips = currentMips;
	}

	public long getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(long currentSize) {
		this.currentSize = currentSize;
	}

	public int getCurrentNumberOfPes() {
		return currentNumberOfPes;
	}

	public void setCurrentNumberOfPes(int currentNumberOfPes) {
		this.currentNumberOfPes = currentNumberOfPes;
	}

	public double getCurrentRam() {
		return currentRam;
	}

	public void setCurrentRam(double currentRam) {
		this.currentRam = currentRam;
	}

	public double getCurrentBw() {
		return currentBw;
	}

	public void setCurrentBw(double currentBw) {
		this.currentBw = currentBw;
	}

}
