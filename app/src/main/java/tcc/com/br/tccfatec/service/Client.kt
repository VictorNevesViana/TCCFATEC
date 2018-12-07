package tcc.com.br.tccfatec.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tcc.com.br.tccfatec.service.interfaces.Service

class Client{

    val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun createService(): Service = retrofit.create(Service::class.java)
}