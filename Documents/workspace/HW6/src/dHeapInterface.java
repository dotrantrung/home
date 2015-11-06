public interface dHeapInterface<T>{
	
	public boolean isEmpty();
	public void  add(T a);
	public T  remove(int index);
	public boolean isFull();
	public void printHeap();
	
}