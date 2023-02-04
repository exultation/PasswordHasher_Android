package de.exultation.passwortgenerator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MainViewModel : ViewModel()
{
    private val internalSecurerKey = "EOWLhc+VE7MiQfC0cTJeQA=="
    var key : MutableLiveData<String> = MutableLiveData("")
    var alias : MutableLiveData<String> = MutableLiveData("")
    var password : MutableLiveData<String> = MutableLiveData("")

    private fun normalizeKey(key : String) : String
    {
        var nKey = key
        if (key.length < 16)
        {
            val missing = 16 - key.length

            for(i in  0 until missing )
            {
                nKey += Char(60+i)
            }
        }
        return nKey
    }

    public fun encryptAlias()
    {


        alias.value?.let {
            val normalizedKey = normalizeKey(key.value ?: internalSecurerKey)
            val sha = MessageDigest.getInstance("SHA-256")
            val normalizedKeyByte = normalizedKey.toByteArray()
            val normalizedKeyDigest = sha.digest(normalizedKeyByte)
            val normalizedKeyDigest16 = normalizedKeyDigest.slice(0..15)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKeySpec =  SecretKeySpec(normalizedKeyDigest16.toByteArray(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE , secretKeySpec)
            val encoded = cipher.doFinal(it.toByteArray())
            val result = Base64.getEncoder().encode(encoded)
            var sResult = String(result)
            sResult = sResult.dropLast(2)
            password.value = sResult.takeLast(12)
        }

    }


}