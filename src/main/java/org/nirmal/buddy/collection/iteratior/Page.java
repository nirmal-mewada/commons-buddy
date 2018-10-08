package org.nirmal.buddy.collection.iteratior;

import java.io.Serializable;

/**
 *
 * @author nirmal.s
 *
 */
public class Page implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4255883826251039072L;

    public static final Long DEFAULT_PAGE_SIZE = 100L;
    public static final Long DEFAULT_PAGE_NUMBER = 1L;
    public static final Long MAX_PAGE_SIZE = 1000l;
    public static final Long MIN_VALID_NUMBER = 1l;

    private Long pageNo = DEFAULT_PAGE_NUMBER;
    private Long pageSize = DEFAULT_PAGE_SIZE;

    private String sortColumn;
    private Long offset;
    private Long totalRecords;

    public Page() {
        super();
    }

    public Page(Long pageNo, Long pageSize, String sortColumn) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }


    @Override
    public String toString() {
        return "Page [" + (pageNo != null ? "pageNo=" + pageNo + ", " : "") + (pageSize != null ? "pageSize=" + pageSize + ", " : "")
                + (sortColumn != null ? "sortColumn=" + sortColumn + ", " : "") + (offset != null ? "offset=" + offset + ", " : "")
                + (totalRecords != null ? "totalRecords=" + totalRecords : "") + "]";
    }
}
