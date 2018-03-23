package com.ll.program.practice.thread;
/**
 * ���캯���з����ڲ��ࣺ���ܵ����ⲿ�໹δ������ɼ�������
 * @author ll
 *
 */
public class ThisEscape {

	public ThisEscape() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(ThisEscape.this);
			}
		});
	}
	
	public void doSomething(){
		System.out.println("this escape");
	}
	
	@Override
	public String toString() {
		return "�����ⲿ��";
	}
}

class MyThread extends Thread{
	public MyThread() {}
	
	public MyThread(Runnable task){
		
	}
}