package com.btca.bootcampalarm.util;

import com.btca.bootcampalarm.dto.BootcampUpdateDto;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

@Component
public class MailUtils {

    public boolean mailFormatCheck(String mail) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                .matcher(mail)
                .matches();
    }

    public String makeMessageFromDto(String mail, BootcampUpdateDto bootcampUpdateDto) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("안녕하세요 ");
        stringBuilder.append(mail);
        stringBuilder.append(" 님\n현재 구독 중인 \"");
        stringBuilder.append(bootcampUpdateDto.getBootcampName());
        stringBuilder.append(' ');
        stringBuilder.append(bootcampUpdateDto.getGeneration());
        stringBuilder.append("\" 의 모집 공고가 공개되었습니다.\n");
        stringBuilder.append("\n모집분야 : ");
        stringBuilder.append(bootcampUpdateDto.getField());
        stringBuilder.append("\n모집 시작일 : ");
        stringBuilder.append(dateFormat.format(bootcampUpdateDto.getRegStartDate()));
        stringBuilder.append("\n모집 종료일 : ");
        stringBuilder.append(dateFormat.format(bootcampUpdateDto.getRegEndDate()));
        stringBuilder.append("\n캠프 시작일 : ");
        stringBuilder.append(dateFormat.format(bootcampUpdateDto.getCampStartDate()));
        stringBuilder.append("\n캠프 종료일 : ");
        stringBuilder.append(dateFormat.format(bootcampUpdateDto.getCampEndDate()));
        stringBuilder.append("\n지원 링크 : ");
        stringBuilder.append(bootcampUpdateDto.getApplyLink());
        stringBuilder.append("\n\n상기 정보는 자동 수집된 정보로 잘못된 정보가 포함될 수 있습니다. 단순 참고용으로 활용하시고 정확한 정보는 공식 공고를 참고해주세요.\n");

        return stringBuilder.toString();
    }
}
