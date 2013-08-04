package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversion {

	public static void main(String[] args) {
		BufferedReader is = null;
		PrintWriter os = null;
		String s = null;
		Queue<Unit> units = new LinkedList<Unit>();
		Queue<String> sign = new LinkedList<String>();
		try {
			// 获得输入流
			is = new BufferedReader(new InputStreamReader(new FileInputStream(
					"input.txt")));
			os = new PrintWriter("output.txt");
			// 设定小数输出格式
			DecimalFormat fmt = new DecimalFormat("0.##");
			
			os.println("527091041@qq.com\n");

			while ((s = is.readLine()) != null) {
				String[] sa = s.split(" ");
				if (isConversion(s)) {
					Unit.rateList
							.put(new Unit(0.0, sa[1], Unit.singleToPlural
									.get(sa[1])), Double.valueOf(sa[3]));
				} else if (!s.equals("")) {
					for (int i = 0; i < sa.length; i++) {
						switch (i % 3) {
						case 0:
							Double number = Double.valueOf(sa[i]);
							Unit unit;
							if (number > 1) {
								unit = new Unit(number,
										Unit.pluralToSingle.get(sa[i + 1]),
										sa[i + 1]);
							} else {
								unit = new Unit(number, sa[i + 1],
										Unit.singleToPlural.get(sa[i + 1]));
							}
							units.add(unit);
							break;
						case 2:
							sign.add(sa[i]);
							break;
						}
					}
					Double result = units.poll().toMeter();
					while (!sign.isEmpty()) {
						result = calculate(result, units.poll().toMeter(),
								sign.poll());
					}
					os.println(fmt.format(result) + " m");
				}
			}
			/*
			 * for (Unit u : Unit.rateList.keySet()) {
			 * 
			 * System.out.println(u + "\t" + Unit.rateList.get(u)); } for(Unit l
			 * : units){ System.out.println(l); } for(String c : sign){
			 * System.out.println(c); }
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (os != null)
					os.close();
			}
		}
	}

	/**
	 * 验证字符串是否为转换式
	 * 
	 * @param s
	 *            字符串s
	 * @return 若为转换式，则返回true ，否则为false
	 */
	public static boolean isConversion(String s) {
		String regex = "^1 \\w+ = \\d+.\\d+ m$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * 计算两值的结果
	 * 
	 * @param a
	 * @param b
	 * @param sign
	 * @return
	 */
	public static Double calculate(Double a, Double b, String sign) {

		switch (sign.charAt(0)) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		default:
			return 0.0;
		}
	}
}
