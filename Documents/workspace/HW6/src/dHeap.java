import java.util.*;

public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T>{
	private int d;
	private int maxSize;
	private T[] heapArray;
	private int elementCount;

	dHeap(int dChild, int size){
		d = dChild;
		heapArray = (T[]) new Comparable[size];
		elementCount = 0;
	}
	/**
	*	Find the parent index of an child index
	*	@param index- child index is used to find its parent
	*/
	private int findParent(int index){
		return ((index-1) / this.d);
	}
	/**
	*	Get i-th child index of element "parent"
	*	@param parent parent index
	*	@param k the index of parent kth -child 
	*/
	private int getNthChildIndex(int parent, int k){
		return (parent * this.d) + k;
	}

	/**
	*	Bubble up element when it is still smaller than its parents.
	*/
	private void bubbleUp(int index){
		T temp = heapArray[index];
		int parentIndex = findParent(index);
		while (index > 0 && (heapArray[index].compareTo(heapArray[parentIndex]) <0 )){
			
			heapArray[index] =  heapArray[parentIndex];
			heapArray[parentIndex] = temp;
			index = parentIndex;
			parentIndex = findParent(index);
		}
	}
	/**
	*	Find the smallest child of "parent Index" 
	*	@param parentIndex index of the parent, so we can use to find child indexes
	*/
	private int findSmallestChildIndex(int parentIndex){
		int smallest = getNthChildIndex(parentIndex, 1);
		//return -1 when parent index have no child. 
		if (smallest >= elementCount){
			return -1;
		}
		int cursor = 2;
		int cursorIndex = getNthChildIndex(parentIndex, cursor);
		int endIndexOfChild = getNthChildIndex(parentIndex, d);
		while (cursorIndex <= endIndexOfChild && cursorIndex < elementCount){
			if (heapArray[cursorIndex].compareTo(heapArray[smallest]) < 0 ){
				smallest = cursorIndex;
			}
			cursor++;
			cursorIndex = getNthChildIndex(parentIndex, cursor);
		}
		return smallest;

	}

	/**
	*	Trickle down from an index until the heap is complete.
	* 	@param index the index which we start to trickle down.
	*/
	private void trickleDown(int index){
		int smallestChildIndex = findSmallestChildIndex(index);
		while ( smallestChildIndex != -1 )
		{
			if (heapArray[index].compareTo(heapArray[smallestChildIndex]) > 0)
			{
					T temp = heapArray[index];
					heapArray[index] = heapArray[smallestChildIndex];
					heapArray[smallestChildIndex] = temp;
			}
			else break;
			index = smallestChildIndex;
			smallestChildIndex = findSmallestChildIndex(index);
			
		}

	}
	/**
	*	Double size of the heap.
	*/
	private void resizeHeap(){
		int newSize = maxSize * 2;
		T[] newArray = (T[]) new Comparable[newSize];

		for (int i = 0 ; i < maxSize; i ++){
			newArray[i] = heapArray[i];
		}
		this.heapArray = newArray;

	}
	/**
	*	Check if the heap is full and ready to extend the size.
	*/
	public boolean isFull(){
		return (elementCount==(maxSize-1));
	}
	/**
	*	This function is to add new data to heap.
	* 	Double the heap array if the heap is full.
	* 	@param newData new data value to add to the heap
	*/
	public void add(T newData){
		if (isFull())
			resizeHeap();
		heapArray[elementCount] = newData;

		bubbleUp(elementCount);
		elementCount++;

	}
	/**
	 * Check if the heap array is empty
	 */
	public boolean isEmpty(){
		return (elementCount == 0) ;
	}
	/**
	*	This function is to remove an element in the heap.
	* 	@param toBeDelete index of element to be deleted
	*/
	public T remove(int index){
		if (!isEmpty()){
			T temp = heapArray[index];
			heapArray[index] = heapArray[elementCount-1];
			elementCount--;
			trickleDown(index);
			return temp;
		}
		else 
			return null;
	}
	/**
	 * Print the heap Array
	 */
	public void  printHeap(){
		for (int i =0; i< elementCount; i++){
			System.out.print(heapArray[i]+ " ");
		}
		System.out.println();
	}
	public static void main(String [] args){
//		dHeap<Integer> test = new dHeap<Integer>(3);
//		int i = 0;
//		while(i<=10){
//			Scanner reader = new Scanner(System.in);
//			System.out.print("Input: " );
//			Integer p = reader.nextInt();
//			test.add((Integer)p);
//			test.printHeap();
//			i++;			
//		}
//		while(i<=20){
//			Scanner reader = new Scanner(System.in);
//			System.out.print("Remove: " );
//			Integer p = reader.nextInt();
//			test.remove(p);
//			test.printHeap();
//			i++;			
//		}
	}

}