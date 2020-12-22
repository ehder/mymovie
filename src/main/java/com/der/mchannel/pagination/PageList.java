package com.der.mchannel.pagination;

import java.util.List;

public class PageList<T> {
	
	private int buttonsToShow = 3;  //默认显示5个按钮		5 buttons displayed by default
    private List<T> itemList;
    private int pageSize;   //每页的数据条数 	Number of data per page
    private int pageIndex;  //当前在第几页，从0开始		Currently on page, starting from 0
    private int pageOffset; //当前页开始的数据条数 		The number of data at the beginning of the current page
    private int totalCount; //一共有几条记录		There are several records
    private int totalPageCount; //一共有几页		A total of several pages
    private int startPage;  //开始页		Start page
    private int endPage;    //结束页		End page

    public PageList(int pageSize, int pageIndex) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageOffset = calculatePageOffset(pageSize, pageIndex);
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        pageOffset = calculatePageOffset(pageSize, pageIndex);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        pageOffset = calculatePageOffset(pageSize, pageIndex);
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        //totalPageCount = (totalCount + pageSize -1) / pageSize;
        //totalPageCount = (totalCount - 1) / pageSize + 1;
        totalPageCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        calculateStartEndPage();
    }
    
    
    //https://stackoverflow.com/questions/17944/how-to-round-up-the-result-of-integer-division

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getButtonsToShow() {
        return buttonsToShow;
    }

    public void setButtonsToShow(int buttonsToShow) {
        this.buttonsToShow = buttonsToShow;
    }

    private int calculatePageOffset(int pageSize, int pageIndex) {
        return pageSize * pageIndex;
    }

    public void calculateStartEndPage() {
        int halfPagesToShow = getButtonsToShow() / 2;
        System.out.println("halfPagesToShow : "+halfPagesToShow );

        if (totalPageCount <= getButtonsToShow()) {
            setStartPage(1);
            setEndPage(totalPageCount);
        } else if (pageIndex - halfPagesToShow <= 0) {
            setStartPage(1);
            setEndPage(getButtonsToShow());
        } else if (pageIndex + halfPagesToShow == totalPageCount) {
            setStartPage(pageIndex - halfPagesToShow);
            setEndPage(totalPageCount);
        } else if (pageIndex + halfPagesToShow > totalPageCount) {
            setStartPage(totalPageCount - getButtonsToShow() + 1);
            setEndPage(totalPageCount);
        } else {
            setStartPage(pageIndex - halfPagesToShow);
            setEndPage(pageIndex + halfPagesToShow);
        }
    }

    @Override
    public String toString() {
        return "PagedList{" +
                "buttonsToShow=" + buttonsToShow +
                ", itemList=" + itemList +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", pageOffset=" + pageOffset +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                '}';
    }

}
