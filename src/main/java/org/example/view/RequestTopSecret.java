package org.example.view;

import java.util.List;

public record RequestTopSecret(
        List<TelescopeView> telescopes,
        List<SatelliteView> satellites) {
}
