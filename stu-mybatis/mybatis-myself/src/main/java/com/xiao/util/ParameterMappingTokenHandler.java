package com.xiao.util;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public  class ParameterMappingTokenHandler  implements TokenHandler {

        private final List<ParameterMapping> parameterMappings = new ArrayList<>();


        public List<ParameterMapping> getParameterMappings() {
                return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
                parameterMappings.add(buildParameterMapping(content));
                return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
                ParameterMapping parameterMapping = new ParameterMapping(content);
                return parameterMapping;
        }

}