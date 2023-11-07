
package genproject;

public interface IterartionListener<C extends CreateChromosomes<C>, T extends Comparable<T>> {

    void update( Genetics<C, T> environment );
    
}
