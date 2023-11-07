
package genproject;

public interface CalculateFitness<C extends CreateChromosomes<C>, T extends Comparable<T>> {

	
	T calculate(C chromosome);

}
