package com.waracle.cakemgr.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CakeTest {

    @Test
    void shouldReturnTrueForCakesWithSameTitle() {
        final Cake cake1 = new Cake();
        cake1.setTitle("Cake Title");
        final Cake cake2 = new Cake();
        cake2.setTitle("Cake Title");

        assertThat(cake1.equals(cake2)).isTrue();
    }

    @Test
    void shouldReturnFalseForCakesWithDifferentTitle() {
        final Cake cake1 = new Cake();
        cake1.setTitle("Cake Title 1");
        final Cake cake2 = new Cake();
        cake2.setTitle("Cake Title 2");

        assertThat(cake1.equals(cake2)).isFalse();
    }

    @Test
    void shouldReturnSameIntegerForCakesWithSameTitle() {
        final Cake cake1 = new Cake();
        cake1.setTitle("Cake Title");
        final Cake cake2 = new Cake();
        cake2.setTitle("Cake Title");

        assertThat(cake1.hashCode()).isEqualTo(cake2.hashCode());
    }

    @Test
    void shouldReturnDifferentIntegerForCakesWithDifferentTitle() {
        final Cake cake1 = new Cake();
        cake1.setTitle("Cake Title 1");
        final Cake cake2 = new Cake();
        cake2.setTitle("Cake Title 2");

        assertThat(cake1.hashCode()).isNotEqualTo(cake2.hashCode());
    }
}
