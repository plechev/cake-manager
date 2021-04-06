package com.waracle.cakemgr.rest;

import com.waracle.cakemgr.domain.Cake;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CakeResponseTest {

    @Test
    void shouldCompareDifferentCakesByTitle() {
        final Cake c1 = new Cake();
        c1.setTitle("Cake A");
        CakeResponse r1 = new CakeResponse(c1);
        final Cake c2 = new Cake();
        c2.setTitle("Cake B");
        CakeResponse r2 = new CakeResponse(c2);

        assertThat(r1).isLessThan(r2);
        assertThat(r2).isGreaterThan(r1);
    }

    @Test
    void shouldCompareSameCakesByTitle() {
        final Cake c1 = new Cake();
        c1.setTitle("Cake A");
        CakeResponse r1 = new CakeResponse(c1);
        final Cake c2 = new Cake();
        c2.setTitle("Cake A");
        CakeResponse r2 = new CakeResponse(c2);

        assertThat(r1).isEqualByComparingTo(r2);
        assertThat(r2).isEqualByComparingTo(r1);
    }
}
