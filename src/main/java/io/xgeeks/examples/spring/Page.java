package io.xgeeks.examples.spring;

public final class Page {

    private final long page;

    private final long size;

    public Page(long page, long size) {
        this.page = page;
        this.size = size;
    }


    public long getLimit() {
        return size;
    }

    public long getOffset() {
        return (page - 1) * size;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
