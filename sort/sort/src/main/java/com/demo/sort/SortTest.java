package com.demo.sort;

import org.junit.Test;

public class SortTest {
	
	
	/**
	 * 1.直接插入排序
	基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
	好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
	也是排好顺序的。如此反复循环，直到全部排好顺序。
	 */
	@Test
	public void insertSort(){
		int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		for(int i=1;i<data.length;i++){
			int temp=data[i];
			int j=i-1;
			for(;j>=0&&temp<data[j];j--){
				data[j+1]=data[j];
			}
			data[j+1]=temp;
		}
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
	
	@Test
	public void insertSort2(){
		int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		doInsertSort(data,1,0);
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
	
	private void doInsertSort(int[] data,int step,int start){
		for(int i=start+step;i<data.length;i+=step){
			int temp=data[i];
			int j=i-step;
			for(;j>=0&&temp<data[j];j-=step){
				data[j+step]=data[j];
			}
			data[j+step]=temp;
		}
	}
	
	/**
	 * 2.   希尔排序（最小增量排序）
	 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
	 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
	 */
	@Test
	public void shellSort(){
		int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		double len=data.length;
		while(true){
			len=Math.ceil(len/2);
			int step=(int)len;
			for(int ci=0;ci<step;ci++){
				doInsertSort(data,step,ci);
			}
			if(step==1){
				break;
			}
		}
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
	
	/**
	 * 3.简单选择排序
		基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
		然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
	 */
	@Test
	public void simpleChoose(){
		int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		for(int i=0,len=data.length;i<len-1;i++){
			int minValueIndex=i;
			int minValue=data[i];
			for(int j=i;j<data.length-1;j++){
				if(data[j]<minValue){
					minValue=data[j];
					minValueIndex=j;
				}
			}
			if(minValueIndex!=i){
				swap(data,minValueIndex,i);
			}
		}
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
	
	
	
	private void swap(int[] data,int a,int b){
		int temp=data[a];
		data[a]=data[b];
		data[b]=temp;
	}
	
}
