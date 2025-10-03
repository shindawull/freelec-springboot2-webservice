package com.dawull.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // assertj 테스트 검증 라이브러리의 검증 메소드
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /*
         * assertj 역시 Junit에서 자동으로 라이브러리 등록을 해줌
         * Junit의 기본assertThat이 아닌 assertj의 assertThat을 사용.
         * 장점:
         * CoreMatchers와 달리 추가적으로 라이브러리가 필요하지않음
         * 자동완성이 좀더 확실하게 지원됨
         * */
    }
}