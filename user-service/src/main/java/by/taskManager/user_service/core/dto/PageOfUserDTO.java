package by.taskManager.user_service.core.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageOfUserDTO<S,T> {
    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<T> content;

    public PageOfUserDTO(Page<S> page,
                         List<T> content) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.total_pages = page.getTotalPages();
        this.total_elements = page.getTotalElements();
        this.first = page.isFirst();
        this.number_of_elements = page.getNumberOfElements();
        this.last = page.isLast();
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Long getTotal_elements() {
        return total_elements;
    }

    public void setTotal_elements(Long total_elements) {
        this.total_elements = total_elements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Integer getNumber_of_elements() {
        return number_of_elements;
    }

    public void setNumber_of_elements(Integer number_of_elements) {
        this.number_of_elements = number_of_elements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
