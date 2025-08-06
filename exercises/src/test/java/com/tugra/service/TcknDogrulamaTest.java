package com.tugra.service;

import com.tugra.repository.UserRepository;
import com.tugra.service.impl.TcknDogrulama;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("TCKN Doğrulama Testleri")
public class TcknDogrulamaTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TcknDogrulama tcknDogrulama;

    @Test
    @DisplayName("Geçerli TCKN - Başarılı doğrulama")
    void tcknDogrulama_GecerliTckn_BasariliSonuc() {

        String gecerliTckn = "43603460444";
        boolean expectedResult = true;

        boolean result = tcknDogrulama.tcknDogrulama(gecerliTckn);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Geçersiz TCKN - Başarısız doğrulama")
    void tcknDogrulama_GecersizTckn_HataliSonBasamak() {

        String gecersizTckn = "43603460443";
        boolean expectedResult = false;

        boolean result = tcknDogrulama.tcknDogrulama(gecersizTckn);
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Geçersiz TCKN - Uzunluk hatası")
    void tcknDogrulama_UzunTcknHatasi() {

        String tckn = "123456789012";
        boolean expectedResult = false;

        boolean result = tcknDogrulama.tcknDogrulama(tckn);
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Geçersiz TCKN - İlk basamak 0 hatası")
    void tcknDogrulama_IlkBasamakSifir() {

        String tckn = "03603460444";
        boolean expectedResult = false;

        boolean result = tcknDogrulama.tcknDogrulama(tckn);
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Geçersiz TCKN - İşlem Başarısız")
    void tcknDogrulama_IslemBasarisiz(){

        String tckn = "12345678992";
        boolean expectedResult = false;

        boolean result = tcknDogrulama.tcknDogrulama(tckn);
        assertEquals(expectedResult, result);

    }

}
