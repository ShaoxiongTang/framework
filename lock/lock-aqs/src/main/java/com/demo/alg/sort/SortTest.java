package com.demo.alg.sort;

import org.junit.Test;

public class SortTest {

	/**
	 * 5.冒泡排序
		基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
		让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换
	 */
	@Test
	public void bubbleSort(){
		 int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		 for(int i=0,len=data.length;i<len-2;i++){
			 for(int j=0;j<data.length-1-i;j++){
				 if(data[j]>data[j+1]){
					 int temp=data[j];
					 data[j]=data[j+1];
					 data[j+1]=temp;
				 }
			 }
		 }
		 for(int i=0;i<data.length;i++){
			 System.out.print(data[i]+" ");
		 }
	}
	
	/**
	 * 6.快速排序
		基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
		然后再用同样的方法递归地排序划分的两部分。
	 */
	@Test
	public void fastSort(){
		
	}
	
	private void doFastSort(int[] data,int start,int end){
		int baseIndex=start;
		for(int i=start+1,len=end-start+1;i<len-1;i++){
			if(data[i]<data[baseIndex]){
				int temp=data[i];
				data[i]=data[baseIndex];
				data[baseIndex]=temp;
				baseIndex=i;
			}
		}
	}
}
