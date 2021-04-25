package com.caffeinegorilla.kubetest.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {
        public static final String USER = "user";
        public static final String TODO = "todo";
    }
}
