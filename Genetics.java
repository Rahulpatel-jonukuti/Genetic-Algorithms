package genproject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class Genetics<C extends CreateChromosomes<C>, T extends Comparable<T>> {

    private static final int all_Chromosomes = Integer.MAX_VALUE;

    private class ChromosomesComparator implements Comparator<C> {

        private final Map<C, T> cache = new WeakHashMap<C, T>();

        @Override
        public int compare(C chr1, C chr2) {
            T fit1 = this.fit(chr1);
            T fit2 = this.fit(chr2);
            int ret = fit1.compareTo(fit2);
            return ret;
        }

        public T fit(C chr) {
            T fit = this.cache.get(chr);
            if (fit == null) {
                f
            .this.fitfunction.calculate(chr);
                this.cache.put(chr, fit);
            }
            return fit;
        }

        ;

		public void clealll() {
            this.cache.clear();
        }
    }

    private final ChromosomesComparator chrcompar;

    private final CalculateFitness<C, T> fitfunction;

    private Population_A<C> pop;

    private final List<IterartionListener<C, T>> itrlisten = new LinkedList<IterartionListener<C, T>>();

    private boolean end = false;

    private int pccount = all_Chromosomes;

    private int itr = 0;

    pu
(Population<C> population, CalculateFitness<C, T> fitnessFunc) {
        this.pop = population;
        this.fitfunction = fitnessFunc;
        this.chrcompar = new ChromosomesComparator();
        this.pop.sortpopbyfit(this.chrcompar);
    }

    public void methodevolve() {
        int psize = this.pop.getSize();

        Population<C> genpop = new Population<C>();

        for (int i = 0; (i < psize) && (i < this.pccount); i++) {
            genpop.addChromosome(this.pop.getChrByIndex(i));
        }

        for (int i = 0; i < psize; i++) {
            C chr = this.pop.getChrByIndex(i);
            C mutated = chr.mutate();

            C otherChromosome = this.pop.getRandomChr();
            List<C> crossovered = chr.crossover(otherChromosome);

            genpop.addChromosome(mutated);
            for (C c : crossovered) {
                genpop.addChromosome(c);
            }
        }

        genpop.sortpopbyfit(this.chrcompar);
        genpop.trim(psize);
        this.pop = genpop;
    }

    public void methodevolve(int count) {
        this.end = false;

        for (int i = 0; i < count; i++) {
            if (this.end) {
                break;
            }
            this.methodevolve();
            this.itr = i;
            for (IterartionListener<C, T> l : this.itrlisten) {
                l.update(this);
            }
        }
    }

    public int getITR() {
        return this.itr;
    }

    public void end() {
        this.end = true;
    }

    public Population<C> getpop() {
        return this.pop;
    }

    public C get_BEST() {
        return this.pop.getChrByIndex(0);
    }

    public C get_WORST() {
        return this.pop.getChrByIndex(this.pop.getSize() - 1);
    }

    public void setparentchrscount(int parentChromosomesCount) {
        this.pccount = parentChromosomesCount;
    }

    public int getparentchrscount() {
        return this.pccount;
    }

    public void addItrList(IterartionListener<C, T> listener) {
        this.itrlisten.add(listener);
    }

    public void removeItrList(IterartionListener<C, T> listener) {
        this.itrlisten.remove(listener);
    }

    public T fitness(C chromosome) {
        return this.chrcompar.fit(chromosome);
    }

    public void clearall() {
        this.chrcompar.clealll();
    }
}
