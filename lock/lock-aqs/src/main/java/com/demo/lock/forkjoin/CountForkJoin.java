package com.demo.lock.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CountForkJoin extends RecursiveTask<Integer>{
	
	private static final long serialVersionUID = 3798331181755456281L;
	
	private static final int THRESHOLD=1000;
	
	private int start;
	private int end;
	
	public CountForkJoin(int start,int end){
		this.start=start;
		this.end=end;
	}
	
	public Integer addAll(){
		int sum=0;
		for(int i=start;i<end+1;i++){
			sum+=i;
		}
		return sum;
	}

	@Override
	protected Integer compute() {
		int sum=0;
		int len=end-start+1;
		System.out.println("start="+start+";end="+end+";len="+len);
		if(len<=THRESHOLD){
			System.out.println("len="+len+";直接计算不再拆分");
			for(int i=start;i<end+1;i++){
				sum+=i;
			}
		}else{
			int middle=(end+start) >>> 1;
			CountForkJoin join1=new CountForkJoin(start,middle);
			CountForkJoin join2=new CountForkJoin(middle+1,end);
			join1.fork();
			join2.fork();
			Integer result1=join1.join();
			Integer result2=join2.join();
			System.out.println(join1+"("+start+","+middle+"): "+result1);
			System.out.println(join2+"("+(middle+1)+","+end+"): "+result2);
			sum=result1+result2;
		}
		return sum;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		CountForkJoin task=new CountForkJoin(1,2000);
		System.out.println(forkJoinPool.submit(task).get());
	}
	
}
