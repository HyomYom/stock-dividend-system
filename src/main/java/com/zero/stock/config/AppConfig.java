package com.zero.stock.config;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean //자동 완성을 위한 Bean -> 메모리 사용이 크다.
    public Trie<String, String> trie(){
        return new PatriciaTrie<>();
    }
}
