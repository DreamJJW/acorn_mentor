package com.acorn_mentor.acorn_mentor.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationService {
    private static final int BAR_LENGTH = 5;
    public List<Integer> getPaginationBarNums(int currentPageNum, int totalPageNum) {
        return null;
    }

    public int currentBAR_LENTGH() {
        return BAR_LENGTH;
    }
}
