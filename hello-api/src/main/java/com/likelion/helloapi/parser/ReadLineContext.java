package com.likelion.helloapi.parser;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReadLineContext<T> {
    private Parser<T> parser;

    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "euc-kr"));

        String str;
        br.readLine(); // 한 줄 날리기

        while ((str = br.readLine()) != null) {
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱 도중 문제가 발생하여 넘어갑니다. 파일 내용: %s\n", str.substring(0, 20));
            }
        }
        br.close();
        return result;

    }
}


