import java.util.Comparator;

public class AvengerComparatorFreqDescending implements Comparator<Avenger> {

	@Override
	/**
	 * Total order:
	 * descending order of total frequency 
	 * in case of tie, in ascending alphabetical order of alias
	 */
	public int compare(Avenger a1, Avenger a2) {
		int diff = a2.getFrequency() - a1.getFrequency();
		if (diff == 0) {
			return a1.getHeroAlias().compareTo(a2.getHeroAlias());
		}
		return diff;
	}

}
