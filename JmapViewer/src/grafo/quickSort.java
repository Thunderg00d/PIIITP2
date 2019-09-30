package grafo;

class quickSort 
{ 
	public void quicksort(Double arr[], int begin, int end) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);
	 
	        quicksort(arr, begin, partitionIndex-1);
	        quicksort(arr, partitionIndex+1, end);
	    }
	}
	
	private int partition(Double arr[], int begin, int end) {
	    Double pivot = arr[end];
	    int i = (begin-1);
	 
	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;
	 
	            Double swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }
	 
	    Double swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;
	 
	    return i+1;
	}
	
} 
/*This code is contributed by Rajat Mishra */
