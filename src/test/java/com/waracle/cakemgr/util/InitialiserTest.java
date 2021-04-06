package com.waracle.cakemgr.util;

import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InitialiserTest {

    @InjectMocks
    private Initialiser underTest;

    @Mock
    private CakeRepository cakeRepositoryMock;

    @Captor
    private ArgumentCaptor<Set<Cake>> cakesCaptor;

    @Test
    void shouldIngestCakesDataFromJson() {

        ReflectionTestUtils.setField(underTest, "cakesDataLocation", getClass().getResource("/cakes.json").toExternalForm());

        underTest.initialiseDatabase(null);

        verify(cakeRepositoryMock).saveAll(cakesCaptor.capture());

        assertThat(cakesCaptor.getValue()).hasSize(5);
    }

    @Test
    void shouldThrowExceptionWhenIngestingFails() {

        ReflectionTestUtils.setField(underTest, "cakesDataLocation", "bogus-path");

        assertThrows(BeanInitializationException.class, () -> {
            underTest.initialiseDatabase(null);
        });
    }


}
