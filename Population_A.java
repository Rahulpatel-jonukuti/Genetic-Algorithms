
package genproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Population<C extends CreateChromosomes<C>> implements Iterable<C> {

	private static final int dnum_chr = 32;

	private List<C> chr = new ArrayList<C>(dnum_chr);

	private final Random r = new Random();

	public void addChromosome(C chromosome) {
		this.chr.add(chromosome);
	}

	public int getSize() {
		return this.chr.size();
	}

	public C getRandomChr() {
		int numOfChromosomes = this.chr.size();
		int indx = this.r.nextInt(numOfChromosomes);
		return this.chr.get(indx);
	}

	public C getChrByIndex(int indx) {
		return this.chr.get(indx);
	}

	public void sortpopbyfit(Comparator<C> chromosomesComparator) {
		Collections.shuffle(this.chr);
		Collections.sort(this.chr, chromosomesComparator);
	}

	
	public void trim(int len) {
		this.chr = this.chr.subList(0, len);
	}

	@Override
	public Iterator<C> iterator() {
		return this.chr.iterator();
	}

}
