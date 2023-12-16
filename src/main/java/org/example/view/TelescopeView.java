package org.example.view;


import java.util.List;

public record TelescopeView(
        String name,
        Double distance,
        String unit,
        List<String> message ) {


}
