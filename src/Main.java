import java.util.*;

public class Main {

	Random rand;
	
	int length = 10;
	
	int[] list = new int[length];
	int[] sortedList = new int[length];
	
	public static void main(String[] args) {
		
		new Main();
		
	}
	
	public Main() {
		
		rand = new Random();
		
		generateList();
		long now = System.currentTimeMillis();
		Arrays.sort(sortedList);
		double elapsed = (double)(System.currentTimeMillis() - now) / 1000.0;
		System.out.println("Finished in " + elapsed + " seconds");
		
		generateList();
		now = System.currentTimeMillis();
		quickSort2(sortedList, 0);
		elapsed = (double)(System.currentTimeMillis() - now) / 1000.0; 
		System.out.println("Finished in " + elapsed + " seconds");
		
	}
	
	public void generateList() {
		
		for(int i = 0; i < list.length; i++) {
			list[i] = rand.nextInt(length*2);
			sortedList = list;
		}
		
	}
	
	//Most efficient, easiest, cleanest
	public void quickSort2(int[] aList, int offset) {
		
		int rp = aList.length - 2;
		int lp = 0;
		int pivot = aList[aList.length - 1];
		
		int lPoint;
		int rPoint;
		
		while(rp > lp) {
			
			while(aList[lp] < pivot) {
				lp++;
			} lPoint = aList[lp];
			
			while(aList[rp] > pivot) {
				rp--;
			} rPoint = aList[rp];
		
			aList[lp] = rPoint;
			aList[rp] = lPoint;
			
		} aList[rp] = pivot;
		
		System.out.println("Pivot: " + pivot);
		System.out.println(Arrays.toString(aList));
		
	}
	
	public void bubbleSort() {
		
		int swapCounter = 0;
		boolean isOffset = false;
		boolean sorted = false;
		
		int tempNum1 = 0;
		int tempNum2 = 0;
		
		while(!sorted) {
			
			swapCounter = 0;
			
			for(int i = isOffset ? 0 : 1; i < sortedList.length - 1; i += 2) {
				
				if(sortedList[i] <= sortedList[i + 1]) {
					
					sortedList[i] = sortedList[i];
					sortedList[i + 1] = sortedList[i + 1];
					
				} else {
					
					tempNum1 = sortedList[i];
					tempNum2 = sortedList[i + 1];
					
					sortedList[i] = tempNum2;
					sortedList[i + 1] = tempNum1;
					
					swapCounter++;
					
				}
				
			}
			
			if(swapCounter == 0) {
				sorted = true;
			}
			isOffset = !isOffset;
		
		}
		
	}
	
	public void quickSort() {
		
		int testRun = 0;
		
		int pivot = 0;
		int tempNum2;
		int pivotLocale;
		int lastPivot;
		
		while(testRun == 0) {
			
			pivot = sortedList[sortedList.length - 1];
			pivotLocale = sortedList.length - 1;
			
			for(int i = 0; i < sortedList.length; i++) {
				
				if(sortedList[i] > pivot && i < pivotLocale) {
					
					tempNum2 = sortedList[i];
					
					sortedList[i] = pivot;
					sortedList[pivotLocale] = tempNum2;
					pivotLocale = i;
					
					
				}
				
				if(sortedList[i] < pivot && i > pivotLocale) {
					
					tempNum2 = sortedList[i];
					
					sortedList[i] = pivot;
					sortedList[pivotLocale] = tempNum2;
					lastPivot = pivotLocale;
					pivotLocale = i;
					i = lastPivot;
					
				}
				
			}
			
			sortedList[pivotLocale] = pivot;
			
			try {
				
				int lowArray[] = Arrays.copyOfRange(sortedList, 0, pivotLocale);
				int highArray[] = Arrays.copyOfRange(sortedList, pivotLocale + 1, sortedList.length);
				//System.out.println(Arrays.toString(sortedList));
				
				if(lowArray.length > 0) {
					quickSortList(lowArray, 0);
				}
				
				if(highArray.length > 0) {
					quickSortList(highArray, pivotLocale + 1);
				}
				
			} catch(IllegalArgumentException e) {  }
			
			testRun++;
			
		}
		
	}

	public void quickSortList(int[] aList, int offset) {
		
		int pivot = 0;
		int tempNum2;
		int pivotLocale;
		int lastPivot;
		
		pivot = aList[aList.length - 1];
		pivotLocale = aList.length - 1;
		
		for(int i = 0; i < aList.length; i++) {
			
			//aList[i] - pivot > 0
			//i - pivotLocale < 0
			//end negative
			
			if(aList[i] > pivot && i < pivotLocale) {
				
				tempNum2 = aList[i];
				aList[i] = pivot;
				aList[pivotLocale] = tempNum2;
				pivotLocale = i;
				
				
			}
			
			//aList[i] - pivot < 0
			//i - pivotLocale > 0
			//end negative
			
			if(aList[i] < pivot && i > pivotLocale) {
				
				tempNum2 = aList[i];
				aList[i] = pivot;
				aList[pivotLocale] = tempNum2;
				lastPivot = pivotLocale;
				pivotLocale = i;
				i = lastPivot;
				
			}
			
		}
		
		sortedList[pivotLocale + offset] = pivot;
		
		try {
			
			if(Arrays.copyOfRange(aList, 0, pivotLocale).length > 0) {
				quickSortList(Arrays.copyOfRange(aList, 0, pivotLocale), offset);
			}
			
			if(Arrays.copyOfRange(aList, pivotLocale + 1, aList.length).length > 0) {
				quickSortList(Arrays.copyOfRange(aList, pivotLocale + 1, aList.length), offset + pivotLocale + 1);
			}
			
		} catch(IllegalArgumentException e) { }
		
	}
	
	public boolean isSorted() {
		
		for(int i = 0; i < sortedList.length - 1; i += 2) {
			
			if(sortedList[i] > sortedList[i + 1]) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
}
