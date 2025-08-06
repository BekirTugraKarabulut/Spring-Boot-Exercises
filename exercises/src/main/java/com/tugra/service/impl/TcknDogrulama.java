package com.tugra.service.impl;

import com.tugra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TcknDogrulama {

    @Autowired
    private UserRepository userRepository;

    public boolean tcknDogrulama(String tckn){

        boolean result = false;

        Integer[] tcknDizi = tckn.chars()
                .mapToObj(Character::getNumericValue)
                .toArray(Integer[]::new);

        if(tcknDizi[0] != 0 && tcknDizi[10] % 2 != 1 && tckn.length() == 11) {

            int teklerinToplami = tcknDizi[0] + tcknDizi[2] + tcknDizi[4] + tcknDizi[6] + tcknDizi[8];
            int ciftlerinToplami = tcknDizi[1] + tcknDizi[3] + tcknDizi[5] + tcknDizi[7];

            int teklerinCarpimi = teklerinToplami * 7;
            int sonuc = teklerinCarpimi - ciftlerinToplami;

            int ilk10basamakToplami = teklerinToplami + ciftlerinToplami + tcknDizi[9];

            result = !(ilk10basamakToplami % 10 != tcknDizi[10] || sonuc % 10 != tcknDizi[9]);
        }

        return result;
    }
}
