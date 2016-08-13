package org.iti.callback.serviceimpl;

import org.iti.callback.service.ComputeCallBack;

public class Main {

	public static void main(String[] args) {
		new TestCallBack().compute(100, new ComputeCallBack() {
			
			@Override
			public void onComputeEnd() {
				System.out.println("end CallBack");
			}
		});
	}
}
