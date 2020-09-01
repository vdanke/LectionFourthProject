package org.step.lection.fourth.project.example;

import java.util.Objects;

public abstract class Transport {

    protected Double power;
    protected String version;

    public Transport(Double power, String version) {
        this.power = power;
        this.version = version;
    }

    public Double getPower() {
        return power;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(version, transport.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, version);
    }
}
