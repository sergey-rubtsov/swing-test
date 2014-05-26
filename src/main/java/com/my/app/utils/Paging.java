package com.my.app.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * User: RubtsovSL
 * Date: 03.10.12
 * Time: 15:25
 */
public class Paging implements Pageable {
    
    private int pageNumber;

    private int pageSize;

    private int pageOffset;

    private Sort pageSort;
    
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return pageOffset;
    }

    @Override
    public Sort getSort() {
        return pageSort;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public void setPageSort(Sort pageSort) {
        this.pageSort = pageSort;
    }

    public Paging(int pageNumber, int pageSize, int pageOffset, Sort pageSort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageOffset = pageOffset;
        this.pageSort = pageSort;
    }
}
