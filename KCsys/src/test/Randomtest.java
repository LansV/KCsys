package test;

import java.util.HashSet;

public class Randomtest {
	public static void main(String[] args) {
		int neednum = 4;
		int count1=0;
		int count2=0;
		int count3=0;
		int count4=0;
		int count5=0;
		int count6=0;
		int count7=0;
		int count8=0;
		int count9=0;
		for(int t=0;t<10;t++){
			HashSet<Integer> set = new HashSet<Integer>();
			randomSet(0, 9, neednum, set);
			System.out.print("第"+(t+1)+"次抽选值：" );
			for (int i : set) {
				System.out.print(i+" ");
				if(i==1){
					count1++;
				}
				if(i==2){
					count2++;
				}
				if(i==3){
					count3++;
				}
				if(i==4){
					count4++;
				}
				if(i==5){
					count5++;
				}
				if(i==6){
					count6++;
				}
				if(i==7){
					count7++;
				}
				if(i==8){
					count8++;
				}
				if(i==9){
					count9++;
				}
			}
			System.out.println();
		}
		int t=count1+count2+count3+count4+count5+count6+count7+count8+count9;
		System.out.println(t);
		System.out.println("1 抽取次数："+count1+" 概率："+(float)count1/t*100);System.out.println("2 抽取次数："+count2+" 概率："+(float)count2/t*100);
		System.out.println("3 抽取次数："+count3+" 概率："+(float)count3/t*100);
		System.out.println("4 抽取次数："+count4+" 概率："+(float)count4/t*100);System.out.println("5 抽取次数："+count5+" 概率："+(float)count5/t*100);
		System.out.println("6 抽取次数："+count6+" 概率："+(float)count6/t*100);
		System.out.println("7 抽取次数："+count7+" 概率："+(float)count7/t*100);System.out.println("8 抽取次数："+count8+" 概率："+(float)count8/t*100);
		System.out.println("9 抽取次数："+count9+" 概率："+(float)count9/t*100);
	}

	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
		if (n > (max - min + 1) || max < min) {
			return;
		}
		int setSize = 0;
		while (true) {
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// 将不同的数存入HashSet中
			setSize = set.size();
			if (setSize >= n) {
				break;
			}
		}
	}
}
