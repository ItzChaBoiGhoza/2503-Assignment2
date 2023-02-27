package assignment2;

import java.util.Comparator;

public class AvengerComparatorFreqAscending implements Comparator<Avenger> {
	
	/*
	 * Comparator override to sort object based on frequency
	 */
	public int compare(Avenger a, Avenger b) {
		int freq1 = a.getFrequency();
		int freq2 = b.getFrequency();
		
		if(freq2-freq1 == 0) {
			return a.getHeroAlias().compareTo(b.getHeroAlias());
		}
		return freq2-freq1;
	}

}
