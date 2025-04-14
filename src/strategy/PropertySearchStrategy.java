package src.strategy;

//generic interface for the search strategy because the search strategy
//is different for each type of search (can return price or list of properties)
public interface PropertySearchStrategy<T> {
    T search(int[] centerAddress, int radius);
} 