package com.acampoverde.ms_account_movement.infraestructure.in.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.acampoverde.ms_account_movement.application.service",
        includeFilters = {
                @ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.REGEX, pattern = "^.+Service")
        } )
public class accountMapper {
}
