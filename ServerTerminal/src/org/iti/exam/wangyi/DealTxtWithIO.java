package org.iti.exam.wangyi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DealTxtWithIO {

	public static void main(String[] args) {
	
		/*File file = new File("E:/upload/image/text.txt");
		new DealTxtWithIO().readText(file, "utf-8");*/
		String s = "帐号1|女|电话1|福建省";
		String[] str = s.split("\\|");
		for(String i : str){
			System.out.println(i);
		}
	}
	
	public String readText(File file, String charset) {

		Map<String, String> parameter = new HashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String lineTxt = null;
			while((lineTxt = br.readLine()) != null){
				System.out.println(lineTxt);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
