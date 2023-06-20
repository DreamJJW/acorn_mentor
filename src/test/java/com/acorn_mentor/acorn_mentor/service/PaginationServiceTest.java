package com.acorn_mentor.acorn_mentor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("페이지네이션 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Void.class)
class PaginationServiceTest {
    private final PaginationService paginationService;
    public PaginationServiceTest(@Autowired PaginationService paginationService){
        this.paginationService = paginationService;
    }

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 반환한다.")
    @MethodSource
    @ParameterizedTest
    void returnPageList(int currentPageNum, int totalPageNum, List<Integer> expectedList) {
        // Given
        // When
        List<Integer> actualList = paginationService.getPaginationBarNums(currentPageNum, totalPageNum);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    static Stream<Arguments>returnPageList() {
        return Stream.of(
                Arguments.arguments(0, 13, List.of(0, 1, 2, 3, 4)),
                Arguments.arguments(1, 13, List.of(0, 1, 2, 3, 4)),
                Arguments.arguments(2, 13, List.of(0, 1, 2, 3, 4)),
                Arguments.arguments(3, 13, List.of(1, 2, 3, 4, 5)),
                Arguments.arguments(4, 13, List.of(2, 3, 4, 5, 6)),
                Arguments.arguments(5, 13, List.of(3, 4, 5, 6, 7)),
                Arguments.arguments(6, 13, List.of(4, 5, 6, 7, 8)),
                Arguments.arguments(10, 13, List.of(8, 9, 10, 11, 12)),
                Arguments.arguments(11, 13, List.of(9, 10, 11, 12)),
                Arguments.arguments(12, 13, List.of(10, 11, 12))
        );
    }

    @DisplayName("현재 설정된 페이지네이션 바의 길이를 알려준다.")
    @Test
    void returnCurrentBarLength() {
        // Given

        // When

        // Then
    }

}