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
			System.out.print("��"+(t+1)+"�γ�ѡֵ��" );
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
		System.out.println("1 ��ȡ������"+count1+" ���ʣ�"+(float)count1/t*100);System.out.println("2 ��ȡ������"+count2+" ���ʣ�"+(float)count2/t*100);
		System.out.println("3 ��ȡ������"+count3+" ���ʣ�"+(float)count3/t*100);
		System.out.println("4 ��ȡ������"+count4+" ���ʣ�"+(float)count4/t*100);System.out.println("5 ��ȡ������"+count5+" ���ʣ�"+(float)count5/t*100);
		System.out.println("6 ��ȡ������"+count6+" ���ʣ�"+(float)count6/t*100);
		System.out.println("7 ��ȡ������"+count7+" ���ʣ�"+(float)count7/t*100);System.out.println("8 ��ȡ������"+count8+" ���ʣ�"+(float)count8/t*100);
		System.out.println("9 ��ȡ������"+count9+" ���ʣ�"+(float)count9/t*100);
	}

	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
		if (n > (max - min + 1) || max < min) {
			return;
		}
		int setSize = 0;
		while (true) {
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// ����ͬ��������HashSet��
			setSize = set.size();
			if (setSize >= n) {
				break;
			}
		}
	}
}
