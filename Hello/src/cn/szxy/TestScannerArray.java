package cn.szxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestScannerArray {
	public static void main(String[] args) {
		List list = new ArrayList();
		Scanner in = new Scanner(System.in);
		System.out.print("请输入数组的长度：");
		int len = in.nextInt();
		System.out.print("请输入一列数：");
		for(int i=0;i<len;i++){
			list.add(in.next());
		}
		

		
		System.out.println(list);
		
	}
}
