import java.util.Vector;

class Heap<T extends Comparable<T>> {

	public static Vector<Integer> heapVector = new Vector<>();
	
	public void percolateUp(Comparable<Integer> item) {
		
		
		int pos = heapVector.size() - 1;
		
		for (; pos >= 1 && item.compareTo(heapVector.get((pos - 1) / 2)) <= 0; pos = (pos - 1) / 2) {
			heapVector.set(pos, heapVector.get((pos - 1) / 2));
		}
		heapVector.set(pos, (Integer) item);
	}

	public void percolateDown(int root) {
		
		if (size() == 0) {
			return;
		}
		int rootIndex = root;
		Comparable<Integer> item = heapVector.get(rootIndex);
		int leftIndex = (rootIndex * 2) + 1;
		int rightIndex = (rootIndex * 2) + 2;
		int smallestIndex;
		
		if (rightIndex >= size()) {
			if (leftIndex >= size()) {
				return;
			} else {
				smallestIndex = leftIndex;
			}
		} else {
			if (heapVector.get(leftIndex).compareTo(heapVector.get(rightIndex)) < 0) {
				smallestIndex = leftIndex;
			} else {
				smallestIndex = rightIndex;
			}
		}
		
		if (heapVector.get(rootIndex).compareTo(heapVector.get(smallestIndex)) >= 0) {
			Comparable<Integer> temp = heapVector.get(smallestIndex);
			heapVector.set(smallestIndex, (Integer) item);
			heapVector.set(rootIndex, (Integer) temp);
			rootIndex = smallestIndex;
			percolateDown(rootIndex);
		}
	}

	public void insert(Comparable<Integer> element) {
		Integer i = (Integer) element;
		heapVector.add(i);
		if (size() != 1) {
			percolateUp(i);
		}
	}

	public void deleteMin() {
		heapVector.set(0, heapVector.get(size() - 1));
		heapVector.remove(size() - 1);
		percolateDown(0);
	}

	public int size() {
		return heapVector.size();
	}

	public Comparable<Integer> getElement(int index) {
		return heapVector.get(index);
	}
}
