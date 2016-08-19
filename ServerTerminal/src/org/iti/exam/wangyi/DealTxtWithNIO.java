package org.iti.exam.wangyi;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DealTxtWithNIO {

	public static void main(String[] args) {

		for (int i = 0; i < 1; i++) {
			long startTime = System.currentTimeMillis();
			Map<String, Integer> map = new DealTxtWithNIO().readText(new File(
					"E:/upload/image/text.txt"), "utf-8");
			List<Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
					map.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					if (o1.getValue()>o2.getValue()) {
						return -1;
					}else if (o1.getValue()<o2.getValue()){
						return 1;
					}else {
						return 0;
					}
				}
			});
			for(Map.Entry<String, Integer> m : list){
				System.out.println("省份："+m.getKey()+"的人数为："+m.getValue());
			}
			System.out.println(System.currentTimeMillis() - startTime);
		}
	}

	public Map<String, Integer> readText(File file, String charset) {

		Map<String, Integer> parameter = new HashMap<String, Integer>();
		FileChannel inChannel = null;
		ByteBuffer bb = ByteBuffer.allocate(2048);
		Charset cs = Charset.forName(charset);
		StringBuilder sb = new StringBuilder();
		FileInputStream inFile = null;
		CharBuffer cb = null;
		try {
			inFile = new FileInputStream(file);
			inChannel = inFile.getChannel();
			bb.clear();
			while (inChannel.read(bb) != -1) {
				bb.flip();
				cb = cs.decode(bb);
				String[] strs = cb.toString().split("\r");
				for (String s : strs) {
					System.out.println(s);
					String[] msg = s.split("\\|");
					String key = msg[msg.length - 1];
					if (!parameter.containsKey(key)) {
						parameter.put(key, 1);
					}else {
						parameter.put(key,
								parameter.get(key) + 1);
					}
				}
				sb.append(cb);
				bb.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inFile != null) {
				try {
					inFile.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return parameter;
	}
}
