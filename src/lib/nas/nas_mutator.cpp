// Created by Yilu, 2023/11/13

#include "nas_mutator.hpp"
#include <ue/app/state_learner.hpp>

namespace nas
{
    // mutator helper functions
    // generate a random security header type
    void mutate_sht(ESecurityHeaderType &sht)
    {
        sht = static_cast<ESecurityHeaderType>(generate_bit(4));
    }

    int gen_sht()
    {
        if (generate_bit(1))
            return generate_bit(4);
        else
            return generate_int(5);
    }

    int mutate_secmod()
    {
        int new_secmod = 1;
        if (nr::ue::state_learner->has_sec_ctx())
            new_secmod += generate_int(3);
        nr::ue::FLAG_SECMOD = new_secmod;
        return new_secmod;
    }
    
    // generate a random bit string with length len
    uint32_t generate_bit(int len)
    {
        if (len > 32)
            throw std::invalid_argument("len must be less than 32");
        uint32_t res = 0;
        for (int i = 0; i < len; i++)
        {
            res <<= 1;
            res += rand() % 2;
        }
        return res;
    }

    // generate a random integer in range [0, range)
    int generate_int(int range)
    {
        return rand() % range;
    }

    // mutate an octet string
    void mutate_octet_string(OctetString &stream)
    {
        int len = stream.length();
        // 50% generate the string with a random length
        int op = generate_int(6);
        switch (op)
        {
        case 0:
            // generate with the same length
            stream.Empty();
            for (int i = 0; len; i++)
                stream.appendOctet(static_cast<uint8_t>(generate_bit(8)));
            break;
        case 1:
            len = generate_int(256); // try the range of 0-255
            stream.Empty();
            for (int i = 0; len; i++)
                stream.appendOctet(static_cast<uint8_t>(generate_bit(8)));
            break;
        case 2:
            // empty the octet string
            stream.Empty();
            break;
        case 3:
            // modify a random byte
            stream.data()[generate_int(len)] = static_cast<uint8_t>(generate_bit(8));
            break;
        case 4:{
            // insert a random byte
            int i = generate_int(len);
            OctetString a = stream.subCopy(0, i);
            OctetString b = stream.subCopy(i);
            a.appendOctet(static_cast<uint8_t>(generate_bit(8)));
            a.append(b);
            stream = a.copy();
            break;}
        case 5:{
            // delete a random byte
            if (len > 0)
            {
                int i = generate_int(len);
                OctetString a = stream.subCopy(0, i);
                OctetString b = stream.subCopy(i+1);
                a.append(b);
                stream = a.copy();
            }
            break;}
        }
    }

    // mutate an string
    void mutate_string(std::string &str)
    {
        int len = str.length();
        // 50% generate the string with a random length
        if (generate_bit(1))
        {
            len = generate_int(256); // try the range of 0-255
            str.resize(len);
            for (int i = 0; i < len; i++)
                str[i] = static_cast<char>(generate_bit(8));
        }
        else
        {
            int pos = generate_int(len);
            int op = generate_int(3);
            if (op == 0)
                str[pos] = static_cast<char>(generate_bit(8)); // modify a random character
            else if (op == 1)
                str.erase(pos, 1); // delete a random character
            else
                str.insert(pos, 1, static_cast<char>(generate_bit(8))); // insert a random character
        }
    }

    // mutate an string
    void mutate_pdu(OctetString &pdu)
    {
        int len = pdu.length();
        int pos = generate_int(len);
        int op = generate_int(3);
        if (op == 0) // modify a random byte
        {
            pdu.data()[pos] = static_cast<uint8_t>(generate_bit(8));
        }
        else if (op == 1) // delete a random byte
        {
            OctetString new_pdu = pdu.subCopy(0, pos);
            if (pos != len - 1)
                new_pdu.append(pdu.subCopy(pos+1));
            pdu.Empty();
            pdu = new_pdu.copy();
        }
        else // insert a random byte
        {
            OctetString new_pdu = pdu.subCopy(0, pos);
            new_pdu.appendOctet(static_cast<uint8_t>(generate_bit(8)));
            new_pdu.append(pdu.subCopy(pos));
            pdu.Empty();
            pdu = new_pdu.copy();
        }
    }
}
