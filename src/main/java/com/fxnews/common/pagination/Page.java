package com.fxnews.common.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Page<T> {

    private List<? extends T> items;
    private int pageSize;
    private int maxPage;

    public Page(List<? extends T> items, int pageSize) {
        this.items = items;
        this.pageSize = pageSize;
        this.maxPage = (int) Math.ceil(items.size() / (double) pageSize);;
    }

    public List<? extends T> getPage(int pageNumber) {
        if (pageNumber < 1 || pageNumber > maxPage) {
            throw new ArrayIndexOutOfBoundsException("Page number out of range");
        }
        return items.subList(
                (pageNumber - 1) * pageSize,
                Math.min(pageNumber * pageSize, items.size())
        );
    }

}
