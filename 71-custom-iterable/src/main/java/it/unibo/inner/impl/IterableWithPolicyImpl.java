package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;

    public IterableWithPolicyImpl(final T[] elements) {
        this.elements = List.of(elements);
    }

    @Override
    public Iterator<T> iterator() {
        return new PlainIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // Volutamente vuoto
    }

    private class PlainIterator implements Iterator<T> {

            private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elements.size();
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
