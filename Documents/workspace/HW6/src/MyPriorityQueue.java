public class MyPriorityQueue<T extends Comparable<? super T>>{
	private dHeap<T> pQueue;

	MyPriorityQueue(int size){
		pQueue = new dHeap(2, size);

	}
	public void add(T newData){
		pQueue.add(newData);
	}
	public T poll(){
		return pQueue.remove(0);
	}

	
}