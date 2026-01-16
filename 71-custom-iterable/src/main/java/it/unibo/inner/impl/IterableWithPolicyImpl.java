package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements) {
        this(elements,
            new Predicate<>() {
                public boolean test (T elem) {
                    return true;
                }
            }
        );
    }

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = List.of(elements);
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    private class FilterIterator implements Iterator<T> {

            private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < elements.size()) {
                T elem = elements.get(currentIndex);
                if (filter.test(elem)) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return elements.get(currentIndex++);
            }
            throw new NoSuchElementException();
        }

    }


}
