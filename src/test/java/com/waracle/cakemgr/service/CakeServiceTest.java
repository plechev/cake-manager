package com.waracle.cakemgr.service;

import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @InjectMocks
    private CakeService underTest;

    @Mock
    private CakeRepository cakeRepositoryMock;

    @Test
    void shouldGetAllAvailableCakes() {

        final List<Cake> storedCakes = List.of(new Cake(), new Cake());
        when(cakeRepositoryMock.findAll()).thenReturn(storedCakes);

        final List<Cake> retrievedCakes = underTest.getAvailableCakes();

        assertThat(retrievedCakes).isEqualTo(storedCakes);
    }

    @Test
    void shouldCreateNewCake() {

        final Cake newCake = new Cake();

        underTest.createCake(newCake);

        verify(cakeRepositoryMock).save(newCake);
        verifyNoMoreInteractions(cakeRepositoryMock);
    }

    @Test
    void shouldDeleteCakeForId() {

        final Integer cakeId = 1234;

        underTest.deleteCake(cakeId);

        verify(cakeRepositoryMock).deleteById(cakeId);
        verifyNoMoreInteractions(cakeRepositoryMock);
    }

}
