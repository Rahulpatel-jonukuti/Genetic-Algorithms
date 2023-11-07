package genproject;

import java.util.List;

public interface CreateChromosomes<C extends CreateChromosomes<C>> {
	
	List<C> crossover( C anotherChromosome );
	
	C mutate();

}
