package com.company.designschema;

import com.company.designschema.util.PinYinUtil;

public class PinYinTest {
	public static void main(String[] args) {
		System.out.println("罗宗楠-----------" + PinYinUtil.toPinyin("罗宗楠"));
		System.out.println("录单测试1-----------" + PinYinUtil.toFirstChar("音乐").toUpperCase());
	}
}
